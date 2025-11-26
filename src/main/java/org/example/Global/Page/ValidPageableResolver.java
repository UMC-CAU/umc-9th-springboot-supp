package org.example.Global.Page;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.Global.exception.InvalidPageException;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class ValidPageableResolver implements HandlerMethodArgumentResolver {

    private static final int DEFAULT_PAGE = 1;   // 프론트 기준 (1부터)
    private static final int PAGE_SIZE = 10;     // 과제 조건

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ValidPageable.class)
                && Pageable.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        String pageParam = request.getParameter("page");
        int page = DEFAULT_PAGE;

        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                throw new InvalidPageException("page 파라미터는 숫자여야 합니다.");
            }
        }

        if (page < 1) {
            throw new InvalidPageException("page 파라미터는 1 이상이어야 합니다.");
        }

        // Spring Data JPA는 0부터 시작하므로 -1
        return PageRequest.of(page - 1, PAGE_SIZE);
    }
}
