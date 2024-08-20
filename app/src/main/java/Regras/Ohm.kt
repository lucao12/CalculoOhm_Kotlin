package Regras

class Ohm {
    fun calcular(value1 : Double, value2 : Double, operador : String): Double {
        if(operador == "voltagem"){
            return (value1 * value2)
        }
        else if(operador == "corrente"){
            return (value1 / value2)
        }
        else{
            return (value1 * value2)
        }
    }
}