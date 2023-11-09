package com.example.demo.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gallery API")
                        .version("1.0")
                        .description("Gallery Management API Documentation " +
                                "\n\n * Data-Collection 섹션의 경우 로컬테스트 용도이며, 배포된 환경에서는 00시에 자동적으로 데이터를 수집 및 업데이트 하여 배포 주소로 들어오셨을 시 사용하시지 않으셔도 괜찮습니다.(배포환경에서 데이터는 200개로 설정해 두었습니다..) " +
                                "\n\n * 데이터 수집의 경우 중복 데이터는 modified_time(수정시간)을 기준으로 값을 비교해 더 최근의 시간을 기준으로 레코드 값을 업데이트 합니다." +
                                "\n\n * ex)서버 API 를 이용해 수정하여도 수정시간이 공공데이터에서 받아오는 수정시간 보다 더 최근이면 업데이트 되지 않습니다."));
    }
}
