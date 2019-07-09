import os
def createConverters(clase):

    if not (os.path.exists("./results/converters")):
        os.mkdir("./results/converters")

    claseminusculas = clase.lower()
    claseMayusculas = clase
    entityToString = """    
    package converters;

    import org.springframework.core.convert.converter.Converter;
    import org.springframework.stereotype.Component;
    import org.springframework.transaction.annotation.Transactional;

    import domain."""+clase+""";

    @Component
    @Transactional
    public class """+clase+"""ToStringConverter implements Converter<"""+clase+""", String> {

        @Override
        public String convert(final """+clase+""" """+claseminusculas+""") {
            String result;

            if ("""+claseminusculas+""" == null)
                result = null;
            else
                result = String.valueOf("""+claseminusculas+""".getId());

            return result;
        }

    }
    """

    StringtoEntityConverter = """    
    package converters;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.core.convert.converter.Converter;
    import org.springframework.stereotype.Component;
    import org.springframework.transaction.annotation.Transactional;

    import repositories."""+clase+"""Repository;
    import domain."""+clase+""";

    @Component
    @Transactional
    public class StringTo"""+clase+"""Converter implements Converter<String, """+clase+"""> {

        @Autowired
        """+clase+"""Repository """+claseminusculas+"""Repository;


        @Override
        public """+clase+""" convert(final String text) {
            """+clase+""" result;
            int id;
            try {
                id = Integer.valueOf(text);
                result = this."""+claseminusculas+"""Repository.findOne(id);
            } catch (final Throwable oops) {
                throw new IllegalArgumentException(oops);
            }

            return result;
        }

    }
    """

    text_file = open(f"results/converters/{clase}ToStringConverter.java", "w")
    text_file.write(entityToString)
    text_file.close()

    text_file = open(f"results/converters/StringTo{clase}Converter.java", "w")
    text_file.write(StringtoEntityConverter)
    text_file.close()