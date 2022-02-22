import dal.OwnerRepository;
import dal.PetRepository;
import dal.TypeRepository;
import domain.dto.input.OwnerInputDto;
import domain.entity.Owner;
import domain.entity.Pet;
import domain.entity.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.util.Date;

@SpringBootApplication(exclude = JacksonAutoConfiguration.class,
        scanBasePackages = {
                "controller",
                "service"
        })
@EntityScan(basePackages = "domain.entity")
@EnableJpaRepositories("dal")
@EnableSwagger2
@EnableScheduling
@EnableAsync
@Slf4j
public class Application implements ApplicationRunner {
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private PetRepository petRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final OwnerInputDto williamDto = new OwnerInputDto(
                1L,
                "Budapest",
                "William",
                "Hanna",
                "12345");
        final Owner william = new Owner(williamDto);

        final OwnerInputDto josephDto = new OwnerInputDto(
                2L,
                "Budapest",
                "Joseph",
                "Barbera",
                "12345");
        final Owner joseph = new Owner(josephDto);

        final Type cat = new Type();
        cat.setName("Cat");
        final Type mouse = new Type();
        mouse.setName("Mouse");

        final Pet tom = new Pet();
        tom.setOwner(ownerRepository.save(william));
        tom.setName("Tom");
        tom.setType(typeRepository.save(cat));
        tom.setBirth_date(new Date());

        final Pet jerry = new Pet();
        jerry.setOwner(ownerRepository.save(joseph));
        jerry.setName("Jerry");
        jerry.setType(typeRepository.save(mouse));
        jerry.setBirth_date(new Date());

        petRepository.save(tom);
        petRepository.save(jerry);
    }

    @Bean("dataSourceInitializer")
    public DataSourceInitializer getDataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();

        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("db/schema.sql")));

        return dataSourceInitializer;
    }

    @Bean("docket")
    public Docket getDocket(@Value("${application.version}") final String version,
                            @Value("${application.name}") final String name) {
        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().version(version)
                        .title(name)
                        .build())
                .tags(new Tag("Owner Controller", "Controller for Owners."),
                        new Tag("Pet Controller", "Controller for Pets"),
                        new Tag("Type Controller", "Controller for Types"));
    }
}
