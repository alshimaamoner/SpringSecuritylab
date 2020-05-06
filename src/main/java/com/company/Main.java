package com.company;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    //If you change Password or userName display Exception" org.springframework.security.authentication.BadCredentialsException: Bad credentials
    String userName="Alshimaa";
    String password="123456";
   public static ApplicationContext  applicationContext3;
   //public static ApplicationContext applicationContext;
    public Main(){
         applicationContext3=new ClassPathXmlApplicationContext("bean4.xml");

        UsernamePasswordAuthenticationToken authenticationToken=createUsernamePasswordAuthenticationToken(userName,password);

        Authentication authentication=authenticate(authenticationToken);

        setSecurityContext(authentication);

        HelloService helloService= (HelloService) applicationContext3.getBean("helloService");
        System.out.println(helloService.sayHello("Jets"));
    }
    //Optional Before Authentication in xml You can Add Roles
    private UsernamePasswordAuthenticationToken createUsernamePasswordAuthenticationToken(String username,String password){
        String[] roles={"ROLE_USER","ROLE_ADMIN","ROLE_ADMIN_UPDATE"};
        List<GrantedAuthority> grantedAuthorityList=new ArrayList<GrantedAuthority>(roles.length);
        for(String role:roles){
            grantedAuthorityList.add(new SimpleGrantedAuthority(role));
        }
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password,grantedAuthorityList);
        return authenticationToken;

    }


    private Authentication authenticate(UsernamePasswordAuthenticationToken authenticationToken){
        //Object Authenticated by using AuthenticationManager
        AuthenticationManager authenticationManager=applicationContext3.getBean(AuthenticationManager.class);
        Authentication authentication=authenticationManager.authenticate(authenticationToken);
        return authentication;
    }
    private void setSecurityContext(Authentication authentication){
        //to use Security Authentication
        SecurityContextImpl securityContext=new SecurityContextImpl();
        securityContext.setAuthentication(authentication);
        //Fully Authentication(username,pass) and App ready to Authorization
        SecurityContextHolder.setContext(securityContext);
    }

    public static void main(String[] args) {
        new Main();
        //System.out.println("@Secured.........");
//      ApplicationContext  applicationContext2=new ClassPathXmlApplicationContext("bean2.xml");
//        UserService userService= (UserService) applicationContext2.getBean("userServiceImpl");
        //User user=new User(1,"Alshimaa","123456");
        //userService.addUser(user);
        // System.out.println(userService.getAllUsers());
        System.out.println("User Permit all............");
        UserService userService2 = (UserService) applicationContext3.getBean("userServiceImpl");
        User user3 = new User(1, "SHEMO", "123456");
        System.out.println(userService2.updateUser(user3));
        System.out.println(userService2.getUser(user3.getId()));
        //  System.out.println("PreAuthorize..........");
        User users = new User(3, "Alshimaaaaaaa", "123456");
        userService2.addUsers(user3);
        userService2.addUsers(users);
        for (User user1 : userService2.getAllUsers()
        ) {
            System.out.println(user1);
        };
              userService2.printUsers(userService2.getAllUsers());
        }

}