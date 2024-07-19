// 클라이언트 (F4)
package F4.F4.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("*")  // 모든 출처 허용 (보안 상 위험할 수 있으니, 실제 배포 환경에서는 구체적인 주소 사용)
        .allowedMethods("GET", "POST", "PUT", "DELETE");
  }
}

