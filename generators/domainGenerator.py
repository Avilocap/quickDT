import os
def createDomain(entityJson):

    entityName = entityJson["Name"]
    entityProperties = entityJson["Properties"]
    extendsTo = entityJson["Extend"]

    propertyBlock = ""
    definitionPropertyBlock = ""

    for prop in entityProperties:
        propName = prop["Name"]
        propCapitalize = propName.capitalize()
        propLowerCase = propName.lower()
        propType = prop["Type"]
        propConstraints = prop["Constraint"]

        denitionProperty = "private " + propType +" " + propLowerCase + ";\n"
        definitionPropertyBlock = definitionPropertyBlock + denitionProperty


        constraintBlock = ""
        for constraint in propConstraints:
            constraintLine = constraint
            constraintBlock = constraintBlock + constraintLine
        


        entityBlock = """
        """+constraintBlock+"""
        public """+propType+""" get"""+propCapitalize+"""() {
	    	return this."""+propLowerCase+""";
	    }

        public void set"""+propCapitalize+"""("""+propType+""" """+propLowerCase+""") {
	    	this."""+propLowerCase+""" = """+propLowerCase+""";
    	}

        """
        propertyBlock = propertyBlock + entityBlock


    if not (os.path.exists("./results/domain")):
        os.mkdir("./results/domain")

    base = """    
    package domain;


    import javax.persistence.Access;
    import javax.persistence.AccessType;
    import javax.persistence.Entity;

    import org.hibernate.validator.constraints.NotBlank;
    import org.hibernate.validator.constraints.Range;
    import org.hibernate.validator.constraints.URL;

    import java.util.Date;
    import java.util.List;

    @Entity
    @Access(AccessType.PROPERTY)
    public class %s extends %s {

            """+definitionPropertyBlock+"""

        // Constructors -----------------------------------------------------------

        public %s() {
                super();
            }


        // Attributes -------------------------------------------------------------
        """+propertyBlock+"""
    }
    
    """
    text_file = open(f"results/domain/{entityName}.java", "w")
    text_file.write(base % (entityName,extendsTo,entityName))
    text_file.close()