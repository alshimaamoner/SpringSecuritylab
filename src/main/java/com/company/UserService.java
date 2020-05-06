package com.company;

import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.core.userdetails.User;
import com.company.*;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;
//3 Way : @Secured / @permit / @PreAuthorize
public interface UserService {
    //Role for Any User
   // @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    //@permit all method allowed to any user
    @PermitAll
    public User getUser(Integer id);
//    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
 //   @PreAuthorize("permitAll()")
   @PostFilter("filterObject.name==authentication.name")
    public List<User> getAllUsers();
    // differ between IS_AUTHENTICATED_ANONYMOUSLY vs not Secured ?????
    //@PreFilter(value = "filterObject.name==authentication.name",filterTarget = "users")
    public void printUsers(List<User> users);
    //Specific user
    @Secured({"ROLE_ADMIN","ROLE_USER_ADD"})
    public User addUser(User user);
   // @Secured({"ROLE_ADMIN_UPDATE"})
    @RolesAllowed("ROLE_ADMIN_UPDATE")
    public String updateUser(User user);
    //@Secured({"ROLE_ADMIN_DELETE"})
    @PreAuthorize("hasRole('ROLE_ADMIN_DELETE')")
    public User deleteUser(Integer id);


    //@PostAuthorize("returnObject.id !=0")
    //@UserPermission
    @PreAuthorize("(hasRole('ADMIN') and #user.name.length()<=8)  ")
    @PostAuthorize(value = "filterObject.name==authentication.name")
    public User addUsers(User user);
    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    void pointCutTest();
}
