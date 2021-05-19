from os.path import dirname, join
import pickle
import numpy as np
from sklearn.decomposition import PCA

def prediccion_modelo(nombre_app, permisos_app):
    modelo = join(dirname(__file__), 'modelo.pkl')
    modelo_pca = join(dirname(__file__), 'modelo_pca.pkl')

    with open(modelo, 'rb') as file:
        pickle_model = pickle.load(file)

    with open(modelo_pca, 'rb') as file2:
        pca_model = pickle.load(file2)

    array = np.array(permisos_app)

    pca_array = pca_model.transform(array.reshape(1,-1))

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
