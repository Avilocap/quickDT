import os
def createService(clase):

    if not (os.path.exists("./results/services")):
        os.mkdir("./results/services")

    claseminusculas = clase.lower()
    claseMayusculas = clase
    base = """    
    package services;

    import domain."""+clase+""";
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories."""+clase+"""Repository;

    import java.util.Collection;

    @Service
    @Transactional
    public class """+clase+"""Service {

        // Managed repository -----------------------------------------------------

        @Autowired
        private """+clase+"""Repository        """+claseminusculas+"""Repository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public """+clase+"""Service() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public """+clase+""" create(){
            """+clase+""" result;
            result = new """+clase+"""();
            return result;
        }
        
        public Collection<"""+clase+"""> findAll() {
            Collection<"""+clase+"""> result;

            result = """+claseminusculas+"""Repository.findAll();
            Assert.notNull(result);

            return result;
        }

        public """+clase+""" findOne(int """+claseminusculas+"""Id) {
            Assert.isTrue("""+claseminusculas+"""Id != 0);

            """+clase+""" result;

            result = """+claseminusculas+"""Repository.findOne("""+claseminusculas+"""Id);
            Assert.notNull(result);

            return result;
        }

        public """+clase+""" save("""+clase+""" """+claseminusculas+""") {
            Assert.notNull("""+claseminusculas+""");

            """+clase+""" result;

            result = """+claseminusculas+"""Repository.save("""+claseminusculas+""");

            return result;
        }

        public void delete("""+clase+""" """+claseminusculas+""") {
            Assert.notNull("""+claseminusculas+""");
            Assert.isTrue("""+claseminusculas+""".getId() != 0);
            Assert.isTrue("""+claseminusculas+"""Repository.exists("""+claseminusculas+""".getId()));

            """+claseminusculas+"""Repository.delete("""+claseminusculas+""");
        }

        // Other business methods -------------------------------------------------


    }

    """
    text_file = open(f"results/services/{clase}Service.java", "w")
    text_file.write(base)
    text_file.close()