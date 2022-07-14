package com.example.demo.validator;

import com.example.demo.entity.Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class BoardValidator implements Validator { // 스프링 부트 유효성 검사
    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Board b = (Board) obj;
        if (StringUtils.isEmpty(b.getGoeHelpContent())) {
            errors.rejectValue("content", "key", "내용을 입력하세요");
        }
    }
}