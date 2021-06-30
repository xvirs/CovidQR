package com.xer.covidqr

class Book constructor(NombApellid: String, DNi:String, Direccion:String, CorreoElectronico:String, Celular:String ) {

    var nombApellid: String
    var DNI: String
    var direccion: String
    var correoElectronico: String
    var celular: String



        get() {
            return field.toUpperCase()
        }

    init {
        this.nombApellid = NombApellid
        this.DNI = DNi
        this.direccion = Direccion
        this.correoElectronico = CorreoElectronico
        this.celular = Celular
    }
}