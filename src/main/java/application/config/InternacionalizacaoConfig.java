package application.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class InternacionalizacaoConfig {

    // Este método configura a fonte de mensagens da aplicação.
    // Ele permite que mensagens de erro e validação sejam carregadas de arquivos externos.
    @Bean
    public MessageSource messageSource() {
        // Cria uma fonte de mensagens que pode ser recarregada sem reiniciar a aplicação.
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        // Define o caminho base do arquivo de mensagens.
        // O arquivo deve estar em src/main/resources com nome messages.properties.
        messageSource.setBasename("Classpath:messages");

        // Define a codificação dos arquivos de mensagens.
        // ISO-8859-1 é comum em português, mas UTF-8 é mais recomendado atualmente.
        messageSource.setDefaultEncoding("ISO-8859-1");

        // Define o idioma padrão da aplicação com base na configuração do sistema.
        messageSource.setDefaultLocale(Locale.getDefault());

        return messageSource;
    }

    // Este método configura o validador padrão do Spring para usar as mensagens personalizadas.
    // Ele permite que as mensagens de erro de validação venham do arquivo messages.properties.
    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean() {
        // Cria uma fábrica de validadores que usa a fonte de mensagens definida acima.
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();

        // Define que as mensagens de validação devem ser buscadas na fonte configurada.
        bean.setValidationMessageSource(messageSource());

        return bean;
    }
}

