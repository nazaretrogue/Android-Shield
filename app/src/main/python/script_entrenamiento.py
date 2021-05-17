from os.path import dirname, join
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.svm import LinearSVC
import numpy as np
from sklearn.decomposition import PCA
import matplotlib.pyplot as plt
from sklearn import metrics
from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score
import pickle
from sklearn.model_selection import cross_val_score

def modelo_svm():
    #data = join(dirname(__file__), '../../../data/data.txt')
    data = join(dirname(__file__), 'data.txt')

    df = pd.read_table(data, sep=',', names=['android.permission.ACCEPT_HANDOVER',
    'android.permission.ACCESS_BACKGROUND_LOCATION',
    'android.permission.ACCESS_COARSE_LOCATION',
    'android.permission.ACCESS_FINE_LOCATION',
    'android.permission.ACCESS_MEDIA_LOCATION',
    'android.permission.ACTIVITY_RECOGNITION',
    'android.permission.ADD_VOICEMAIL',
    'android.permission.ANSWER_PHONE_CALLS',
    'android.permission.BODY_SENSORS',
    'android.permission.CALL_PHONE',
    'android.permission.CAMERA',
    'android.permission.GET_ACCOUNTS',
    'android.permission.PROCESS_OUTGOING_CALLS',
    'android.permission.READ_CALENDAR',
    'android.permission.READ_CALL_LOG',
    'android.permission.READ_CONTACTS',
    'android.permission.READ_EXTERNAL_STORAGE',
    'android.permission.READ_PHONE_NUMBERS',
    'android.permission.READ_PHONE_STATE',
    'android.permission.READ_SMS',
    'android.permission.RECEIVE_MMS',
    'android.permission.RECEIVE_SMS',
    'android.permission.RECEIVE_WAP_PUSH',
    'android.permission.RECORD_AUDIO',
    'android.permission.SEND_SMS',
    'android.permission.USE_SIP',
    'android.permission.WRITE_CALENDAR',
    'android.permission.WRITE_CALL_LOG',
    'android.permission.WRITE_CONTACTS',
    'android.permission.WRITE_EXTERNAL_STORAGE',
    'label'])

    X = df.iloc[:, :-1]
    y = df.iloc[:, -1]

    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, train_size=0.8, random_state=0)

    # El modelo elimina las features que no son útiles para clasificar con al menos un 95% de precisión
    pca = PCA(0.95)
    pca.fit(X_train)
    pca_train = pca.transform(X_train)
    pca_test = pca.transform(X_test)

    # Entrenamos el modelo con el conjunto de datos
    svc = LinearSVC()
    svc.fit(pca_train, y_train)

    # Curva ROC
    metrics.plot_roc_curve(svc, pca_test, y_test)
    plt.show()

    # Comprobamos si hay overfitting
    print(cross_val_score(svc, X, y, cv=5))

    # Predicciones con el modelo entrenado
    prediccion = svc.predict(pca_test)

    # Métricas para comprobar el comportamiento
    print('Accuracy score: ', format(accuracy_score(y_test, prediccion)))
    print('Precision score: ', format(precision_score(y_test, prediccion)))
    print('Recall score: ', format(recall_score(y_test, prediccion)))
    print('F1 score: ', format(f1_score(y_test, prediccion)))

    guardar_modelo(svc)

def guardar_modelo(modelo):
    with open("modelo.pkl",'wb') as file:
        pickle.dump(modelo, file)

def main():
    modelo_svm()

if __name__ == '__main__':
    main()
