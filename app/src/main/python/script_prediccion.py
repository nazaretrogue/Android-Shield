from os.path import dirname, join
import pickle
import numpy as np
from sklearn.decomposition import PCA

def prediccion_modelo(nombre_app, permisos_app):
    # Cargamos el modelo entrenado y el pca
    modelo = join(dirname(__file__), 'modelo.pkl')
    modelo_pca = join(dirname(__file__), 'modelo_pca.pkl')

    with open(modelo, 'rb') as file:
        pickle_model = pickle.load(file)

    with open(modelo_pca, 'rb') as file2:
        pca_model = pickle.load(file2)

    # Transformamos el array de permisos a algo que podamos leer
    array = np.array(permisos_app)

    # Usando el pca, eliminamos las features que no sirven
    pca_array = pca_model.transform(array.reshape(1,-1))

    # Predecimos el tipo de app
    etiqueta = pickle_model.predict(pca_array)

    prediccion = "una app benigna"

    if etiqueta[0] == 1:
        prediccion = "malware"

    return "La app "+nombre_app+" es "+prediccion+"\n"

def main():
    msg = prediccion_modelo()

    return msg

if __name__ == "__main__":
    main()
