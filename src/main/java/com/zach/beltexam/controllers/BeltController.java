package com.zach.beltexam.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import com.zach.beltexam.validators.UserValidator;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.zach.beltexam.models.Show;
import com.zach.beltexam.models.User;
import com.zach.beltexam.models.UserRating;
import com.zach.beltexam.services.UserService;

@Controller
public class BeltController {
	private final UserService userService;
	private final UserValidator userValidator;

	public BeltController(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}

	@RequestMapping("/")
	public String registerForm(@ModelAttribute("user") User user) {
		return "loginReg.jsp";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session,
			RedirectAttributes redAttr) {
		// if result has errors, return the registration page (don't worry about
		// validations just now)
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			redAttr.addFlashAttribute("regError", "Please fill in every box");
			return "redirect:/";
		} else {
			User u = userService.registerUser(user);
			session.setAttribute("userId", u.getId());
			return "redirect:/home";

		}
		// else, save the user in the database, save the user id in session, and
		// redirect them to the /home route
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model,
			HttpSession session, RedirectAttributes redAttr) {
		// if the user is authenticated, save their user id in session
		boolean isAuthenticated = userService.authenticateUser(email, password);
		if (isAuthenticated) {
			User user = userService.findByEmail(email);
			session.setAttribute("userId", user.getId());
			return "redirect:/home";
		} else {
			redAttr.addFlashAttribute("loginError", "Invalid credentials, try again");
			return "loginReg.jsp";
		}
		// else, add error messages and return the login page
	}

	@RequestMapping("/home")
	public String home(HttpSession session, Model model, @Valid @ModelAttribute("show") Show show) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		List<Show> shows = userService.allShows();
		Long id = (Long) session.getAttribute("userId");
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		model.addAttribute("shows", shows);
		return "homePage.jsp";
	}

	@RequestMapping("/newShow")
	public String newShowPage(Model model, @Valid @ModelAttribute("show") Show show) {
		model.addAttribute("show", show);
		return "newShow.jsp";
	}

	@RequestMapping(value = "/addShow", method = RequestMethod.POST)
	public String addShow(@Valid @ModelAttribute("show") Show show, BindingResult result, RedirectAttributes redAttr) {
		if (result.hasErrors()) {
			redAttr.addFlashAttribute("error", "Title and Network must be present");
			return "newShow.jsp";
		}
		if (!show.getTitle().equals(userService.findTitle(show))
				&& !show.getNetwork().equals(userService.findNetwork(show))) {
			redAttr.addFlashAttribute("error", "Title and network must be unique");
			return "redirect:/newShow";
		} else {
			show = userService.createShow(show);
			return "redirect:/home";

		}

	}

	@RequestMapping("/home/show/{id}")
	public String viewShow(@PathVariable("id") Long id, Model model, HttpSession session,
			@ModelAttribute("userrating") UserRating rating) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		Show thisShow = userService.findShow(id);
		List<UserRating> raters = thisShow.getWhoRated();
		model.addAttribute("ratings", raters);
		model.addAttribute("user", user);
		model.addAttribute("thisShow", thisShow);
		model.addAttribute("userrating", rating);
		return "show.jsp";
	}

	@RequestMapping(value = "/rate/{id}", method = RequestMethod.POST)
	public String addRating(@PathVariable("id") Long id, Model model, HttpSession session,
			@ModelAttribute("userrating") UserRating rating, @RequestParam("rating") double showrating) {
		Show show = userService.findShow(id);
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		rating.setRating(showrating);
		userService.addRating(user, show, rating);
		return "redirect:/home";
	}

	@RequestMapping("/editShow/{id}/edit")
	public String editPage(@PathVariable("id") Long id, Model model, @ModelAttribute("show") Show show,
			HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		model.addAttribute("thisShow", userService.findShow(id));
		return "edit.jsp";
	}

	@RequestMapping(value = "/editShow/{id}", method = RequestMethod.PUT)
	public String editShow(@PathVariable("id") Long id, @ModelAttribute("show") Show show, BindingResult result,
			Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		if (result.hasErrors()) {
			return "edit.jsp";
		} else {
			Show editShow = show;
			model.addAttribute("show", editShow);
			model.addAttribute("user", user);
			userService.updateShow(editShow);
			return "redirect:/home";
		}
	}

	@RequestMapping("/show/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		userService.deleteShow(id);
		return "redirect:/home";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";

	}

}
