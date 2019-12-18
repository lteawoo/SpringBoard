package kr.taeu.mvc;

import kr.taeu.mvc.board.domain.BoardVO;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//@MapperScan(basePackages = "classpath:sqlmap/sqlmap-board.xml")
public class MybatisConfig {
    @Bean
    public DataSource dataSource() {
        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.HSQL) //.H2 or .DERBY
                .addScript("classpath:BoardSchema.sql")
                .addScript("classpath:BoardData.sql")
                .build();
        return db;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory (DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory  = new SqlSessionFactoryBean();

        sqlSessionFactory.setDataSource(dataSource);
        Properties mybatisProperties = new Properties();
        mybatisProperties.setProperty("cacheEnabled", "false");
        mybatisProperties.setProperty("useGeneratedKeys", "false");
        mybatisProperties.setProperty("mapUnderscoreToCamelCase", "true");
        sqlSessionFactory.setConfigurationProperties(mybatisProperties);
        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:sqlmap/sqlmap-board.xml");

        sqlSessionFactory.setMapperLocations(res);
        sqlSessionFactory.setTypeAliasesPackage("kr.taeu.mvc.board.domain");

        return sqlSessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession (SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
