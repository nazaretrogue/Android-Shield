import os
import re

pattern = re.compile("android\.permission\.[a-zA-Z_]+")

permisos_peligrosos = ["android.permission.ACCEPT_HANDOVER","android.permission.ACCESS_BACKGROUND_LOCATION","android.permission.ACCESS_COARSE_LOCATION","android.permission.ACCESS_FINE_LOCATION","android.permission.ACCESS_MEDIA_LOCATION","android.permission.ACTIVITY_RECOGNITION","android.permission.ADD_VOICEMAIL","android.permission.ANSWER_PHONE_CALLS","android.permission.BODY_SENSORS","android.permission.CALL_PHONE","android.permission.CAMERA","android.permission.GET_ACCOUNTS","android.permission.PROCESS_OUTGOING_CALLS","android.permission.READ_CALENDAR","android.permission.READ_CALL_LOG","android.permission.READ_CONTACTS","android.permission.READ_EXTERNAL_STORAGE","android.permission.READ_PHONE_NUMBERS","android.permission.READ_PHONE_STATE","android.permission.READ_SMS","android.permission.RECEIVE_MMS","android.permission.RECEIVE_SMS","android.permission.RECEIVE_WAP_PUSH","android.permission.RECORD_AUDIO","android.permission.SEND_SMS","android.permission.USE_SIP","android.permission.WRITE_CALENDAR","android.permission.WRITE_CALL_LOG","android.permission.WRITE_CONTACTS","android.permission.WRITE_EXTERNAL_STORAGE"]

def busca_match_malware():
    # Directorio con las muestras de malware
    directory_path = "data/malware/"
    dir = os.fsencode(directory_path)

    for file in os.listdir(dir):
         filename = os.fsdecode(file)
         if filename.endswith(".json"):
             malware = directory_path+filename
             with open(malware, 'r') as f:
                 for line in f:
                     # Buscamos los permisos en el archivo json y los extraemos
                     # por orden alfabético
                     match = re.findall(pattern, line)
                     checkea_permisos(sorted(match))

def busca_match_benignas():
    total = []

    # Directorio con las muestras de apps benignas
    directory_path = "data/benignas/"
    dir = os.fsencode(directory_path)

    for file in os.listdir(dir):
         filename = os.fsdecode(file)
         if filename.endswith(".json"):
             benigna = directory_path+filename
             with open(benigna, 'r') as f:
                 for line in f:
                     # Buscamos los permisos de forma análoga a lo que hemos
                     # hecho con las muestras de malware
                     match = re.findall(pattern, line)
                     total.append(match)
                 flat_list = [item for sublist in total for item in sublist]
                 checkea_permisos(sorted(flat_list))

def checkea_permisos(permisos_app):
    # Guardaremos los datos en un archivo nuevo
    f = open("data/data.txt", "a")

    # Comprobamos si alguno de los permisos es peligroso, y lo añadimos al
    # archivo con un 1 (true) o un 0 (false)
    for permiso in permisos_peligrosos:
        if permiso in permisos_app:
            f.write("1,")
        else:
            f.write("0,")
            
    # Si estamos preprocesando malware, escribimos un 1 para indicar que es malware
    # Si estamos preprocesando apps benignas, escribimos un 0
    # f.write("1\n")
    f.write("0\n")
    f.close()

def main():
    # busca_match_malware()
    busca_match_benignas()


if __name__ == '__main__':
    main()
