package acc.wordbook.config;

import acc.wordbook.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor())
                .order(1) // 인터셉터 체인 순서
                .addPathPatterns("/**") // 모든 requestURL에 적용
                .excludePathPatterns("/login" // 제외할 list
                    , "/member/signup"
                    , "/api/**/**"
                    , "/logout"
                    , "/css/**"
                    , "/js/**"
                );
    }
}
