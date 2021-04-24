import os
import re

permisos_peligrosos = ["android.permission.ACCEPT_HANDOVER","android.permission.ACCESS_BACKGROUND_LOCATION","android.permission.ACCESS_COARSE_LOCATION","android.permission.ACCESS_FINE_LOCATION","android.permission.ACCESS_MEDIA_LOCATION","android.permission.ACTIVITY_RECOGNITION","android.permission.ADD_VOICEMAIL","android.permission.ANSWER_PHONE_CALLS","android.permission.BODY_SENSORS","android.permission.CALL_PHONE","android.permission.CAMERA","android.permission.GET_ACCOUNTS","android.permission.PROCESS_OUTGOING_CALLS","android.permission.READ_CALENDAR","android.permission.READ_CALL_LOG","android.permission.READ_CONTACTS","android.permission.READ_EXTERNAL_STORAGE","android.permission.READ_PHONE_NUMBERS","android.permission.READ_PHONE_STATE","android.permission.READ_SMS","android.permission.RECEIVE_MMS","android.permission.RECEIVE_SMS","android.permission.RECEIVE_WAP_PUSH","android.permission.RECORD_AUDIO","android.permission.SEND_SMS","android.permission.USE_SIP","android.permission.WRITE_CALENDAR","android.permission.WRITE_CALL_LOG","android.permission.WRITE_CONTACTS","android.permission.WRITE_EXTERNAL_STORAGE"]

def busca_match():
    pattern = re.compile("android\.permission\.[a-zA-Z_]+")

    directory_path = "data/malware/"
    dir = os.fsencode(directory_path)

    for file in os.listdir(dir):
         filename = os.fsdecode(file)
         if filename.endswith(".json"):
             malware = directory_path+filename
             with open(malware, 'r') as f:
                 for line in f:
                     match = re.findall(pattern, line)
                     checkea_permisos(sorted(match))

def checkea_permisos(permisos_app):
    f = open("data/data.txt", "a")
    for permiso in permisos_peligrosos:
        if permiso in permisos_app:
            f.write("1,")
        else:
            f.write("0,")
    f.write("1\n")
    f.close()

def main():
    busca_match()


if __name__ == '__main__':
    main()
