import os
import json
from generators.domainGenerator import createDomain
from generators.repositoriesGenerator import createRepository
from generators.servicesGenerator import createService
from generators.controllersGenerator import createController
from generators.viewsGenerator import createViews
from generators.convertersGenerator import createConverters
from generators.createMisc import createMisc

f = open("Entities.json", "r")
content = f.read()
jsondecoded = json.loads(content)

for entity in jsondecoded["Classes"]:
    clase = entity["Name"]

    if not (os.path.exists("./results")):
        os.mkdir("./results")
        os.mkdir("./results/views")
        os.mkdir("./results/converters")
        os.mkdir("./results/domain")
        os.mkdir("./results/controllers")
        os.mkdir("./results/repositories")
        os.mkdir("./results/services")
        os.mkdir("./results/misc")

    createDomain(entity)
    createRepository(clase)
    createService(clase)
    createController(clase)
    createConverters(clase)
    createViews(entity)
    createMisc(clase)

print("Generation succesful!")