import os
def createRepository(clase):

    if not (os.path.exists("./results/repositories")):
        os.mkdir("./results/repositories")

    base = """    
    package repositories;

    import domain.%s;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface %sRepository extends JpaRepository<%s, Integer> {

    }

    
    """
    text_file = open(f"results/repositories/{clase}Repository.java", "w")
    text_file.write(base % (clase,clase,clase))
    text_file.close()
