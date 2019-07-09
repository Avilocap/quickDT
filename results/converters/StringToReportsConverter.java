    
    package converters;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.core.convert.converter.Converter;
    import org.springframework.stereotype.Component;
    import org.springframework.transaction.annotation.Transactional;

    import repositories.ReportsRepository;
    import domain.Reports;

    @Component
    @Transactional
    public class StringToReportsConverter implements Converter<String, Reports> {

        @Autowired
        ReportsRepository reportsRepository;


        @Override
        public Reports convert(final String text) {
            Reports result;
            int id;
            try {
                id = Integer.valueOf(text);
                result = this.reportsRepository.findOne(id);
            } catch (final Throwable oops) {
                throw new IllegalArgumentException(oops);
            }

            return result;
        }

    }
    