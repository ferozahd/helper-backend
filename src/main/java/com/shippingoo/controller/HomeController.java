// package com.shippingoo.controller;

// import com.shippingoo.domain.User;
// import com.shippingoo.domain.security.PasswordResetToken;
// import com.shippingoo.domain.security.Role;
// import com.shippingoo.domain.service.UserService;
// import com.shippingoo.domain.service.impl.UserSecurityService;
// import com.shippingoo.email.MailConstructor;
// import com.shippingoo.utility.SecurityUtility;

// import org.springframework.beans.factory.annotation.Autowired;

// // import org.springframework.mail.javamail.JavaMailSender;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;
// import javax.servlet.http.HttpServletRequest;
// import java.util.*;

// import java.util.logging.Logger;

// @Controller
// public class HomeController {

//     Logger logger = Logger.getLogger(HomeController.class.getName());

//     // @Autowired
//     // private JavaMailSender mailSender;

//     @Autowired
//     private MailConstructor mailConstructor;

//     @Autowired
//     private UserService userService;

//     @Autowired
//     private UserSecurityService userSecurityService;
//     /*
//      * @RequestMapping("/")
//      * public String index() {
//      * return "index-2";
//      * }
//      */

//     @RequestMapping("/myAccount")
//     public String myAccount() {
//         return "myAccount";
//     }

//     @RequestMapping("/myProfile")
//     public String myProfile() {
//         return "myProfile";
//     }

//     @RequestMapping("/login")
//     public String login(Model model) {
//         model.addAttribute("classActiveLogin", true);
//         return "myAccount";
//     }

//     @RequestMapping("/hours")
//     public String hours() {
//         return "hours";
//     }

//     @RequestMapping("/faq")
//     public String faq() {
//         return "faq";
//     }

//     @RequestMapping("/forgetPassword")
//     public String forgetPassword(
//             HttpServletRequest request,
//             @ModelAttribute("email") String email,
//             Model model) {

//         model.addAttribute("classActiveForgetPassword", true);

//         User user = userService.findByEmail(email);

//         if (user == null) {
//             model.addAttribute("emailNotExist", true);
//             return "myAccount";
//         }

//         String password = SecurityUtility.randomPassword();

//         String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
//         user.setPassword(encryptedPassword);

//         userService.save(user);

//         String token = UUID.randomUUID().toString();
//         userService.createPasswordResetTokenForUser(user, token);

//         String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

//         mailConstructor.constructResetTokenEmail(appUrl, token, user);

//         model.addAttribute("forgetPasswordEmailSent", "true");

//         return "myAccount";
//     }

//     @RequestMapping(value = "/newUser", method = RequestMethod.POST)
//     public String newUserPost(
//             HttpServletRequest request,
//             @ModelAttribute("email") String userEmail,
//             @ModelAttribute("username") String username,
//             @ModelAttribute("roleName") String roleName,
//             Model model) throws Exception {
//         model.addAttribute("classActiveNewAccount", true);
//         model.addAttribute("email", userEmail);
//         model.addAttribute("username", username);
//         model.addAttribute("roleName", roleName);

//         if (userService.findByUsername(username) != null) {
//             model.addAttribute("usernameExists", true);

//             return "myAccount";
//         }

//         if (userService.findByEmail(userEmail) != null) {
//             model.addAttribute("emailExists", true);

//             return "myAccount";
//         }

//         User user = new User();
//         user.setUsername(username);
//         user.setEmail(userEmail);

//         String password = SecurityUtility.randomPassword();

//         String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
//         user.setPassword(encryptedPassword);

//         // Role role = new Role();
//         // role.setRoleId(1);
//         // role.setName("ROLE_USER");
//         // Set<UserRole> userRoles = new HashSet<>();
//         // userRoles.add(new UserRole(user, role));
//         // userService.createUser(user, userRoles);

//         Role userRole = new Role(roleName);
//         Set<Role> roles = new HashSet<>();
//         roles.add(userRole);
//         userService.createUser(user, roles);

//         String token = UUID.randomUUID().toString();
//         userService.createPasswordResetTokenForUser(user, token);

//         // For local work purpose I would not take help of email varifications
//         if (token != null)
//             return "redirect:/newUser?token=" + token;

//         String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

//         mailConstructor.constructResetTokenEmail(appUrl, token, user);
//         model.addAttribute("emailSent", "true");
//         return "myAccount";
//     }

//     @RequestMapping("/newUser")
//     public String newUser(Locale locale, @RequestParam("token") String token, Model model) {
//         PasswordResetToken passToken = userService.getPasswordResetToken(token);

//         if (passToken == null) {
//             String message = "Invalid Token.";
//             model.addAttribute("message", message);
//             return "redirect:/badRequest";
//         }

//         User user = passToken.getUser();
//         String username = user.getUsername();

//         UserDetails userDetails = userSecurityService.loadUserByUsername(username);

//         Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
//                 userDetails.getAuthorities());

//         SecurityContextHolder.getContext().setAuthentication(authentication);

//         model.addAttribute("user", user);

//         model.addAttribute("classActiveEdit", true);

//         return "myProfile";
//     }

//     @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
//     public String updateUserInfo(
//             @ModelAttribute("user") User user,
//             @ModelAttribute("newPassword") String newPassword,
//             Model model) throws Exception {
//         User currentUser = userService.findById(user.getId());

//         if (currentUser == null) {
//             throw new Exception("User not found");
//         }

//         /* check email already exists */
//         if (userService.findByEmail(user.getEmail()) != null) {
//             if (userService.findByEmail(user.getEmail()).getId() != currentUser.getId()) {
//                 model.addAttribute("emailExists", true);
//                 return "myProfile";
//             }
//         }

//         /* check username already exists */
//         if (userService.findByUsername(user.getUsername()) != null) {
//             if (userService.findByUsername(user.getUsername()).getId() != currentUser.getId()) {
//                 model.addAttribute("usernameExists", true);
//                 return "myProfile";
//             }
//         }

//         // update password
//         // *************This code below allow user to reset password with a given random
//         // password*******************

//         /*
//          * if (newPassword != null && !newPassword.isEmpty() &&
//          * !newPassword.equals("")){
//          * BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
//          * String dbPassword = currentUser.getPassword();
//          * if(passwordEncoder.matches(user.getPassword(), dbPassword)){
//          * currentUser.setPassword(passwordEncoder.encode(newPassword));
//          * } else {
//          * model.addAttribute("incorrectPassword", true);
//          * 
//          * return "myProfile";
//          * }
//          * }
//          */

//         BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
//         currentUser.setPassword(passwordEncoder.encode(newPassword));

//         currentUser.setFirstName(user.getFirstName());
//         currentUser.setLastName(user.getLastName());
//         currentUser.setUsername(user.getUsername());
//         currentUser.setEmail(user.getEmail());

//         userService.save(currentUser);

//         model.addAttribute("updateSuccess", true);
//         model.addAttribute("user", currentUser);
//         model.addAttribute("classActiveEdit", true);

//         model.addAttribute("listOfShippingAddresses", true);
//         model.addAttribute("listOfCreditCards", true);

//         UserDetails userDetails = userSecurityService.loadUserByUsername(currentUser.getUsername());

//         Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
//                 userDetails.getAuthorities());

//         SecurityContextHolder.getContext().setAuthentication(authentication);

//         return "myProfile";
//     }


// }
