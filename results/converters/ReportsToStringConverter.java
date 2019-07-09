    
    package converters;

    import org.springframework.core.convert.converter.Converter;
    import org.springframework.stereotype.Component;
    import org.springframework.transaction.annotation.Transactional;

    import domain.Reports;

    @Component
    @Transactional
    public class ReportsToStringConverter implements Converter<Reports, String> {

        @Override
        public String convert(final Reports reports) {
            String result;

            if (reports == null)
                result = null;
            else
                result = String.valueOf(reports.getId());

            return result;
        }

    }
    