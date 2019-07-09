import os
def createMisc(clase):

    if not (os.path.exists("./results/misc")):
        os.mkdir("./results/misc")

    claseminusculas = clase.lower()
    claseMayusculas = clase
    tiles = """    
    <value>/views/"""+claseminusculas+"""/tiles.xml</value>
    """
    i18 = """    
    <value>/views/"""+claseminusculas+"""/messages</value>
    """
    converters = """    
    <bean class="converters."""+clase+"""ToStringConverter" />
	<bean class="converters.StringTo"""+clase+"""Converter" />
    """

    text_file = open(f"results/misc/tiles.txt", "a")
    text_file.write(tiles)
    text_file.close()

    text_file = open(f"results/misc/i18.txt", "a")
    text_file.write(i18)
    text_file.close()

    text_file = open(f"results/misc/converters.txt", "a")
    text_file.write(converters)
    text_file.close()