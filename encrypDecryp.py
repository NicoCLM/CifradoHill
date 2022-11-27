# Piza, Hernandez, Villarreal, Camacho 
# Encriptado y Desencriptado de Hill

import string 
import numpy as np
import sys

alphabet_string = string.ascii_uppercase
alphabet_list = list(alphabet_string)

d={}
d2={}


for i in range(len(alphabet_list)):
    d[alphabet_list[i]]=i

for i in range(len(alphabet_list)):
    d2[i]=alphabet_list[i]

# -------------------------------------------------    
# ENCRIPTADO
mensaje = input("Ingrese el mensaje para encriptar: ")
dimension = 0
validacionmsg = False
validacionclv = False
validacioncofactor = False
#
while validacionmsg == False:
    if mensaje.isalpha() == False :
        print('Ingrese un mensaje solo con letra no caracteres distintos')
        mensaje = input("Ingrese el mensaje para encriptar: ")    
    else:
        validacionmsg = True            

clave = input("Ingrese la clave: ")
claveSize = len(clave)
#print(claveSize)
while validacionclv == False:
    if claveSize not in [4, 9]:
        print("")
        print("Ingrese una clave de menor longitud (Longitud permitida de la clave: 9 y 4)")
        print("")
        clave = input("Ingrese la clave: ")
        claveSize = len(clave)
#        print(claveSize)
    else:
        dimension = 3 if claveSize == 9 else 2
        validacionclv = True
#Se crea una matriz
matx = np.identity(dimension, dtype=int)
#Se llena de 0 la matriz
matx.fill(0)
def keyMatrix(clave,matx): 
    mensaje = clave 
    mtrz = matx
    count = 0
    for x in range(dimension): 
        for y in range(dimension): 
            mtrz[x][y] = d[mensaje[count].upper()] 
            count = count + 1
            if count>(len(mensaje)-1): 
                break
        if count > (len(mensaje)-1):
            break
    return mtrz

print("")
print("Matriz Clave")    
print(np.transpose(keyMatrix(clave, matx)))
print("")

def find_mod_inv(a,m):
    for x in range(1,m):
        if((a%m)*(x%m) % m==1):
            return x
                #raise Exception('La inversa modular multiplicativa no existe.')

def becomMatrix(palabra,dimension):
    mensaje=palabra    
    matriz = []
    matRow = int((len(mensaje)/dimension)+1)
    for i in range(matRow):
        matriz.append([25]*dimension) 
    count=0   
    for x in range(matRow):
        for j in range(dimension):
            matriz[x][j]=d[mensaje[count].upper()]
            count=count+1
            if count > len(mensaje)-1:
                break
        if count > len(mensaje)-1:
            break
    return matriz       

print("Matriz del mensaje original")
print(np.transpose(becomMatrix(mensaje,dimension)))
print("")
# a y m  son dos numeros enteros
print("Determinante de la matriz clave")
print(int(np.linalg.det(np.array(keyMatrix(clave, matx))) + 0.5))
print("")

modclavemat = int(np.linalg.det(np.transpose(keyMatrix(clave, matx)))) % 26
print(find_mod_inv(modclavemat, 26))

if int(np.linalg.det(np.array(keyMatrix(clave, matx))) + 0.5) % 2 == 0:
    print("La clave ingresada no cumple con los requisitos ya que la determinante es igual a 0. Ingrese los datos de nuevo (Ejecute de nuevo el programa)")
    sys.exit()
if int(np.linalg.det(np.array(keyMatrix(clave, matx)))) == 0:
    print("La clave ingresada no cumple con los requisitos ya que la determinante es igual a 0. Ingrese los datos de nuevo (Ejecute de nuevo el programa)")
    sys.exit()
if int(np.linalg.det(np.array(keyMatrix(clave, matx)))) % 26 == 0:
    print("La clave ingresada no cumple con los requisitos ya que la determinante es igual a 0. Ingrese los datos de nuevo (Ejecute de nuevo el programa)")
    sys.exit()
if 26 % int(np.linalg.det(np.array(keyMatrix(clave, matx)))) == 0:
    print("La clave ingresada no cumple con los requisitos ya que la determinante es igual a 0. Ingrese los datos de nuevo (Ejecute de nuevo el programa)")
    sys.exit()
if find_mod_inv(modclavemat, 26) == None:
    print("La clave ingresada no cumple con los requisitos ya que la determinante es igual a 0. Ingrese los datos de nuevo (Ejecute de nuevo el programa)")
    sys.exit()



prod= np.dot(np.transpose(np.array(keyMatrix(clave, matx))),np.transpose(np.array(becomMatrix(mensaje,dimension))))
cifrado=[]
count=0
for i in range(len(prod[0])):
    for j in range(len(prod)):
        cifrado.append(d2[prod[j][i]%26])
     
print("")  
print("Producto de la matriz clave y la matriz del mensaje")        
print(prod)
print(" ")
print("Modulo 26 al resultado del producto de las dos matrices ")
print(cifrado)
print(" ")
print("Mensaje encriptado")
print(''.join(cifrado))
print(" ")
# ------------------------------------------------------- #
# DESENCRIPTADO
msg=input("Ingrese el mensaje encriptado: ")
valmsg = False
valcod = False

while valmsg == False:
    if msg.isalpha() == False :
        print('Ingrese un mensaje solo con letra no caracteres distintos')
        msg = input("Ingrese el mensaje para encriptar: ")    
    else:
        valmsg = True
        
cod=input("Ingrese la clave: ")
codSize = len(cod)

while valcod == False:
    if codSize not in [4, 9]:
        print("Ingrese una clave de menor longitud (Longitud permitida de la clave: 9 y 4)")
        cod = input("Ingrese la clave: ")
        codSize = len(cod)
    else:
       valcod = True
def matrix_cofactor(matrix):
    #try:
        determinant = np.linalg.det(matrix)
        if(determinant!=0):
            cofactor = None
            cofactor = np.linalg.inv(matrix) * determinant
            return cofactor
        else:
            False
    #except Exception as e:
        print("No se pudo encontrar la matriz de cofactores")
        return 0

print("")
print("Matriz de cofactores")
print(np.transpose(matrix_cofactor(keyMatrix(cod, matx))))
print("")   
print("")
print("Matriz del mensaje encriptado")
print(np.transpose(becomMatrix(msg,dimension)))
print("")
print("Matriz inversa de la clave")
print(np.linalg.inv(keyMatrix(cod, matx)))
print("")
print("Determinante de la matriz clave")
print(int(np.linalg.det(np.transpose(keyMatrix(cod, matx))))) 
print("")

#

modMat = int(np.linalg.det(np.transpose(keyMatrix(cod, matx)))) % 26
prodf = (find_mod_inv(modMat, 26) * np.transpose(matrix_cofactor(keyMatrix(cod, matx)))) % 26
msgfinal = np.dot(prodf, np.transpose(becomMatrix(msg,dimension))) % 26

print("")
print("Modulo del determinante de la matriz clave")
print(modMat)
print("")
print("Inversa multiplicativa del determinante modulo 26")
print(find_mod_inv(modMat, 26))
print("")
print("Multiplicacion entre la inversa multiplicativa y la matriz de cofactores. Luego modulo 26")
print(prodf)
print("")
print("Matriz del mensaje desencriptado")
print(msgfinal)
print("")
      

result = []
for i in range(len(msgfinal[0])):
    for j in range(len(msgfinal)):
        ele= int(msgfinal[j][i]+0.9) if int(msgfinal[j][i]+0.9) < 26  else 0
        result.append(d2[ele])

print(" ")         
print("Mensaje desencriptado")        
print(''.join(result))
