    
    package converters;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.core.convert.converter.Converter;
    import org.springframework.stereotype.Component;
    import org.springframework.transaction.annotation.Transactional;

    import repositories.SubmissionsRepository;
    import domain.Submissions;

    @Component
    @Transactional
    public class StringToSubmissionsConverter implements Converter<String, Submissions> {

        @Autowired
        SubmissionsRepository submissionsRepository;


        @Override
        public Submissions convert(final String text) {
            Submissions result;
            int id;
            try {
                id = Integer.valueOf(text);
                result = this.submissionsRepository.findOne(id);
            } catch (final Throwable oops) {
                throw new IllegalArgumentException(oops);
            }

            return result;
        }

    }
    