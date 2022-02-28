package com.wanho.searchnearbyplaces.error;


import com.wanho.searchnearbyplaces.constant.ErrorCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(path = "/error", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletResponse response, Model model) {
        HttpStatus status = HttpStatus.valueOf(response.getStatus());
        ErrorCode errorCode = status.is4xxClientError() ? ErrorCode.BAD_REQUEST : ErrorCode.INTERNAL_ERROR;

        model.addAttribute("statusCode", status.value());
        model.addAttribute("errorCode", errorCode);
        model.addAttribute("message", errorCode.getMessage(status.getReasonPhrase()));

        return new ModelAndView("error");
    }

    @RequestMapping("/error")
    public ResponseEntity<CustomErrorDto> error(HttpServletResponse response) {
        HttpStatus status = HttpStatus.valueOf(response.getStatus());
        ErrorCode errorCode = status.is4xxClientError() ? ErrorCode.BAD_REQUEST : ErrorCode.INTERNAL_ERROR;

        return ResponseEntity
                .status(status)
                .body(CustomErrorDto.set(false, errorCode));
    }

}


