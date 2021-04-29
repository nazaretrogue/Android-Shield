from os.path import dirname, join
import pickle
import numpy as np

def prediccion_modelo(nombre_app, permisos_app):
    modelo = join(dirname(__file__), 'modelo.pkl')

    with open(modelo, 'rb') as file:
        pickle_model = pickle.load(file)

    array = np.array(permisos_app)
    etiqueta = pickle_model.predict(array.reshape(1,-1))

    prediccion = "una app benigna"

    if etiqueta[0] == 1:
        prediccion = "malware"

    print("La app "+nombre_app+" es "+prediccion+"\n")

def main():
    prediccion_modelo()

if __name__ == "__main__":
    main()
