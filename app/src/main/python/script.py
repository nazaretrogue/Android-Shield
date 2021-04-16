import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import MultinomialNB
from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score

df = pd.read_table('../../../../../../data/data.txt', sep=',', names=['android.permission.ACCESS_COARSE_LOCATION','android.permission.ACCESS_FINE_LOCATION','android.permission.ACCESS_MOCK_LOCATION','android.permission.BODY_SENSORS','android.permission.CALL_PHONE','android.permission.CAMERA','android.permission.GET_ACCOUNTS','android.permission.PROCESS_OUTGOING_CALLS','android.permission.READ_CALENDAR','android.permission.READ_CALL_LOG','android.permission.READ_CONTACTS','android.permission.READ_EXTERNAL_STORAGE','android.permission.READ_SMS','android.permission.RECEIVE_MMS','android.permission.RECEIVE_SMS','android.permission.RECEIVE_WAP_PUSH','android.permission.RECORD_AUDIO','android.permission.SEND_SMS','android.permission.USE_SIP','android.permission.WRITE_CALENDAR','android.permission.WRITE_CALL_LOG','android.permission.WRITE_CONTACTS','android.permission.WRITE_EXTERNAL_STORAGE','label'])

X = df.iloc[:, :-1]
y = df.iloc[:, -1]

X_train, X_test, y_train, y_test = train_test_split(X, y, random_state=1)

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
