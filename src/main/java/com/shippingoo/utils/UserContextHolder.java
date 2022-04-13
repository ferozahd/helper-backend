package com.shippingoo.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

@Slf4j
public class UserContextHolder {

  private static final ThreadLocal<UserContext> currentUser = new ThreadLocal<>();

  public static final UserContext getContext() {
    UserContext context = currentUser.get();

    if (context == null) {
      context = createEmptyContext();
      currentUser.set(context);
    }
    return currentUser.get();
  }

  public static String getToken() {
    return getContext().getToken();
  }



  public static final void setContext(UserContext context) {
    Assert.notNull(context, "Only non-null UserContext instances are permitted");
    currentUser.set(context);
  }

  public static final UserContext createEmptyContext() {
    return new UserContext();
  }

  public static void clear() {
    currentUser.set(null);
  }
  public  static String getFullName(){return getContext().getFullname();}

  public static String getUsername() {
    return getContext().getUsername();
  }

  public static Long getUserId(){
    return getContext().getUserId();
  }
}
