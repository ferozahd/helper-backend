package com.shippingoo.Entity;



import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Entity
public class PasswordResetToken {

   private static final int EXPIRATION=60*24;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;

    private Long userid;

    private Date expiryDate;

    public PasswordResetToken() {
    }

    public PasswordResetToken(final String token) {
        this.token = token;

        this.expiryDate=calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes){

         Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return  new Date (cal.getTime().getTime());
    }

    public  void updateToken(final String token){
        this.token=token;
        this.expiryDate=calculateExpiryDate(EXPIRATION);
    }

}
