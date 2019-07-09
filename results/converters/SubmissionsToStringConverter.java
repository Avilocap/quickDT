    
    package converters;

    import org.springframework.core.convert.converter.Converter;
    import org.springframework.stereotype.Component;
    import org.springframework.transaction.annotation.Transactional;

    import domain.Submissions;

    @Component
    @Transactional
    public class SubmissionsToStringConverter implements Converter<Submissions, String> {

        @Override
        public String convert(final Submissions submissions) {
            String result;

            if (submissions == null)
                result = null;
            else
                result = String.valueOf(submissions.getId());

            return result;
        }

    }
    