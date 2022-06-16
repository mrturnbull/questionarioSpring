package com.dunedin.sensorx.questionario.exception;
 
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
@ControllerAdvice
public class MultipartExceptionHandler {
 
    @ExceptionHandler(MultipartException.class)
    public void handleError(MultipartException e, RedirectAttributes redirectAttributes) {
 		
 		System.out.println("Erro no upload: " + e.getCause().getMessage());
 		/*
        redirectAttributes.addFlashAttribute("error", e.getCause().getMessage());
        return "redirect:/uploadForm";
        */
    }
}