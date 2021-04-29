from os.path import dirname, join
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import MultinomialNB
from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score
import pickle

def naives_bayes():
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

    # Entrenamiento
    naive_bayes = MultinomialNB()
    naive_bayes.fit(X_train, y_train)

    # Predicciones con el modelo entrenado
    prediccion = naive_bayes.predict(X_test)

    # Métricas para comprobar el comportamiento
    print('Accuracy score: ', format(accuracy_score(y_test, prediccion)))
    print('Precision score: ', format(precision_score(y_test, prediccion)))
    print('Recall score: ', format(recall_score(y_test, prediccion)))
    print('F1 score: ', format(f1_score(y_test, prediccion)))

    guardar_modelo(naive_bayes)

def guardar_modelo(modelo):
    with open("modelo.pkl",'wb') as file:
        pickle.dump(modelo, file)

def main():
    naives_bayes()

if __name__ == '__main__':
    main()
