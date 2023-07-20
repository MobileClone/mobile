package com.example.mobileapp.listing;

import com.example.mobileapp.DbConnection;
import com.example.mobileapp.user.User;
import com.example.mobileapp.user.UserService;
import com.google.firebase.database.DataSnapshot;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class ListingController {

    private final ListingService listingService;

    @Autowired
    public ListingController(ListingService listingService) {
       this.listingService = listingService;
    }

    @GetMapping (value = "/createListing")
    public ModelAndView createListing(@RequestParam(name = "token", required = false) String customToken,Model model) throws ExecutionException, InterruptedException {
        ModelAndView modelAndView = new ModelAndView("/createListing");
        if(customToken != null){
            UserService userService = new UserService();
            boolean check = userService.isValidToken(customToken);
            if(check){
                Long id = userService.getUserId(customToken);
                System.out.println(id);
                Listing listing = new Listing();
                model.addAttribute("listing",listing);
            } else{
                System.out.println("nice try");
                RedirectView redirectView = new RedirectView();
                redirectView.setUrl("/login");
                return new ModelAndView(redirectView);
            }
        }else{
            System.out.println("nice try");
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/login");
            return new ModelAndView(redirectView);
        }
        return modelAndView;
    }
    @PostMapping("/createListing")
    public String createListing(
            @ModelAttribute("listing") Listing listing,
            HttpServletRequest request,
            Errors errors) throws ExecutionException, InterruptedException{
        DbConnection db = new DbConnection();
        db.dbConnection();
        listingService.createListing(listing);
        return "redirect:/homepageLogged";
    }

    @GetMapping (value = "/")
    public String getAllListings(Model model) throws ExecutionException, InterruptedException {
        List<Listing> listingList = listingService.getAllListings();
        model.addAttribute("listings",listingList);
        return "homepage";
    }

    @GetMapping (value = "/homepageLogged")
    public ModelAndView test(@RequestParam(name = "token", required = false) String customToken,Model model) throws ExecutionException, InterruptedException {
        ModelAndView modelAndView = new ModelAndView("/homepageLogged");
        if(customToken != null){
            UserService userService = new UserService();
            boolean check = userService.isValidToken(customToken);
            if(check){
                Long id = userService.getUserId(customToken);
                System.out.println(id);
                List<Listing> listingList = listingService.getAllListings();
                model.addAttribute("listings",listingList);
            } else{
                System.out.println("nice try");
                RedirectView redirectView = new RedirectView();
                redirectView.setUrl("/login");
                return new ModelAndView(redirectView);
            }
        }else{
            System.out.println("nice try");
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/login");
            return new ModelAndView(redirectView);
        }
        return modelAndView;
    }

    @GetMapping (value = "/listings")
    public ModelAndView getAllListings(@RequestParam(name = "token", required = false) String customToken,Model model) throws ExecutionException, InterruptedException {
        ModelAndView modelAndView = new ModelAndView("/listings");
        if(customToken != null){
            UserService userService = new UserService();
            boolean check = userService.isValidToken(customToken);
            if(check){
                Long id = userService.getUserId(customToken);
                System.out.println(id);
                List<Listing> listingList = listingService.getAllListings();
                model.addAttribute("listings",listingList);
            } else{
                System.out.println("nice try");
                RedirectView redirectView = new RedirectView();
                redirectView.setUrl("/login");
                return new ModelAndView(redirectView);
            }
        }else{
            System.out.println("nice try");
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/login");
            return new ModelAndView(redirectView);
        }
        return modelAndView;
    }
}
