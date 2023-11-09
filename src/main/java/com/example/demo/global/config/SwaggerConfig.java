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
                                "\n\n - Data-Collection 섹션의 경우 로컬테스트 용도이며, 배포된 환경에서는 00시에 자동적으로 데이터를 수집 및 업데이트 하여 배포 주소로 들어오셨을 시 사용하시지 않으셔도 괜찮습니다.(배포환경에서 데이터는 200개로 설정해 두었습니다..) " +
                                "\n\n   - galContentId (갤러리 컨텐츠 ID)를 기본 식별자로 사용하고, modifiedTime (수정 시간)을 데이터가 최신인지 확인하는 기준으로 사용합니다. 데이터베이스에 이미 같은 galContentId를 가진 레코드가 있고, API로부터 가져온 modifiedTime이 더 최근이라면, 기존 레코드를 업데이트합니다. galContentId가 데이터베이스에 존재하지 않는 새로운 데이터라면 새 레코드를 추가합니다." +
                                "\n\n   - 갤러리 생성의 경우 galContentId (갤러리 컨텐츠 ID)가 공공데이터 galContentId (갤러리 컨텐츠 ID)와 겹치지 않게하기 위해 UUID로 구성된 다른 식별자를 사용합니다."));
    }
}
