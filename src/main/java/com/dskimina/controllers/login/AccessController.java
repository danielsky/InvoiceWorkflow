package com.dskimina.controllers.login;

import com.dskimina.exceptions.PageNotFoundException;
import com.dskimina.forms.ResetPasswordForm;
import com.dskimina.logic.BusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AccessController {

    public static final String RESET_PASSWORD_FORM_ATTR = "resetPasswordForm";
    @Autowired
    private BusinessLogic logic;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String showLoginPage(){
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public RedirectView logout(HttpSession session){
        session.invalidate();
        return new RedirectView("/login?logout", true);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authorize")
    public RedirectView authorizeProcess(){
        return new RedirectView("/index",true);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reset-pass")
    public String showResetPasswordPage(){
        return "reset-pass";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reset-pass")
    public RedirectView showResetPasswordPageProcess(@RequestParam String email, RedirectAttributes ra){
        boolean result = logic.createResetPasswordLinkForEmail(email);
        ra.addFlashAttribute("result", result);
        return new RedirectView("reset-pass");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reset-pass/{id}")
    public String showResetPasswordForm(@PathVariable String id, ModelMap model) throws PageNotFoundException{
        if(logic.isResetCodeInvalid(id)){
            throw new PageNotFoundException();
        }
        model.addAttribute("id", id);
        if(!model.containsAttribute(RESET_PASSWORD_FORM_ATTR)) {
            model.addAttribute(RESET_PASSWORD_FORM_ATTR, new ResetPasswordForm());
        }
        return "reset-pass-form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reset-pass/{id}")
    public RedirectView showResetPasswordFormProcess(@PathVariable String id, @Valid ResetPasswordForm resetPasswordForm, BindingResult bindingResult, RedirectAttributes ra){
        if(bindingResult.hasErrors()){
            ra.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + RESET_PASSWORD_FORM_ATTR, bindingResult);
            ra.addFlashAttribute(RESET_PASSWORD_FORM_ATTR, resetPasswordForm);
            return new RedirectView("/reset-pass/"+id, true);
        }
        if(!resetPasswordForm.getNewPass().equals(resetPasswordForm.getRetypedPass())){
            ra.addFlashAttribute("passAreDifferent", true);
            return new RedirectView("/reset-pass/"+id, true);
        }

        logic.resetPasswordForId(id, resetPasswordForm.getNewPass());
        ra.addFlashAttribute("passwordReset", true);
        return new RedirectView("/login", true);
    }
}
