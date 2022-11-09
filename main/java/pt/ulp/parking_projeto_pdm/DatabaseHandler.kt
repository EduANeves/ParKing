package pt.ulp.parking_projeto_pdm

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.collections.ArrayList


private const val DB_NAME =  "parking"
private const val  DB_VERSION = 1
var nif5 = 0
class  DatabaseHandler (context: Context): SQLiteOpenHelper(context, "parking",  null , 1) {


    val TABLE_NAME = "utilizador"
    val nif = "nif"
    val name = "nome"
    val snome = "sobrenome"
    val username = "username"
    val password = "password"
    val email = "email"
    val TABLE_NAME2 = "veiculo"
    val codigo = "codigo"
    val consumo = "consumo"
    val tipo_veiculo = "tipo_veiculo"
    val modelo = "modelo"
    val ponto_atual = "ponto_atual"
    val ativo = "ativo"
    val TABLE_NAME3 = "tipo_veiculo"
    val id_tipo = "id_tipo"
    val tipo = "tipo"
    val TABLE_NAME4 = "precario"
    val id_p = "id_p"
    val tipo_p = "tipo_precario"
    val horas = "horas"
    val preco = "preco"
    val TABLE_NAME5 = "ponto_pr"
    val id_ponto = "id_p"
    val zona = "zona"
    val rua = "rua"
    val TABLE_NAME6 = "aluguer"
    val id_aluguer = "id_aluguer"
    val utilizador = "utilizador"
    val veiculo = "veiculo"
    val data_inicio = "data_inicio"
    val data_fim = "data_fim"
    val hora_inicio = "hora_inicio"
    val hora_fim = "hora_fim"
    val zona_partilha = "zona_partilha"
    val zona_entrega = "zona_entrega"
    val ativo2 = "ativo"

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                nif + " INTEGER PRIMARY KEY," +
                name + " TEXT," + snome + " TEXT," +
                username + " TEXT," + password + " TEXT," + email + " TEXT);"
        db.execSQL(CREATE_TABLE)
        val CREATE_TABLE1 = "CREATE TABLE " + TABLE_NAME2 + " (" +
                codigo + " INTEGER PRIMARY KEY," +
                consumo + " INTEGER," + tipo_veiculo + " INTEGER," +
                modelo + " TEXT," + ponto_atual + " INTEGER," + ativo + " INTEGER);"
        db.execSQL(CREATE_TABLE1)
        val CREATE_TABLE2 = "CREATE TABLE " + TABLE_NAME3 + " (" +
                id_tipo + " INTEGER PRIMARY KEY," +
                tipo + " TEXT);"
        db.execSQL(CREATE_TABLE2)
        val CREATE_TABLE3 = "CREATE TABLE " + TABLE_NAME4 + " (" +
                id_p + " INTEGER PRIMARY KEY," +
                tipo_p + " INTEGER," + horas + " TIME," + preco + " FLOAT);"
        db.execSQL(CREATE_TABLE3)
        val CREATE_TABLE4 = "CREATE TABLE " + TABLE_NAME5 + " (" +
                id_ponto + " INTEGER PRIMARY KEY," +
                zona + " TEXT," + rua + " TEXT);"
        db.execSQL(CREATE_TABLE4)
        val CREATE_TABLE5 = "CREATE TABLE " + TABLE_NAME6 + " (" +
                id_aluguer + " INTEGER PRIMARY KEY," +
                utilizador + " INTEGER," + veiculo + " INTEGER," +
                data_inicio + " DATE," + data_fim + " DATE," + hora_inicio + " TIME," +
                hora_fim + " TIME," + zona_partilha + " INTEGER," + zona_entrega + " INTEGER," + ativo2 + " INTEGER);"
        db.execSQL(CREATE_TABLE5)
        val insert1 = "insert into tipo_veiculo values(1,'Carro de 2 portas');"
        val insert2 = "insert into tipo_veiculo values(2, 'Carro de 4 portas');"
        val insert3 = "insert into tipo_veiculo values(3, 'Motociclo');"
        val insert4 = "insert into tipo_veiculo values(4, 'Trotinete');"
        db.execSQL(insert1)
        db.execSQL(insert2)
        db.execSQL(insert3)
        db.execSQL(insert4)
        val insert5 = "insert into precario values (1, 1, '01:00:00', 3.25);"
        val insert6 = "insert into precario values (2, 2, '01:00:00', 4.00);"
        val insert7 = "insert into precario values (3, 3, '01:00:00', 2.50);"
        val insert8 = "insert into precario values (4, 4, '01:00:00', 1.20);"
        db.execSQL(insert5)
        db.execSQL(insert6)
        db.execSQL(insert7)
        db.execSQL(insert8)
        val insert9 = "insert into veiculo values (315499070,12,2,'Hyundai Ioniq 5', 1,0);"
        val insert10 = "insert into veiculo values (509777113,9,2,'Skoda Enyaq', 2,0);"
        val insert11 = "insert into veiculo values (751464789,10,1,'Fiat 500', 3,0);"
        val insert12 = "insert into veiculo values (992055960,7,2,'Kia EV6', 1,0);"
        val insert13 = "insert into veiculo values (140965196,14,2,'Ford Mustang Mach-E', 3,0);"
        val insert14 = "insert into veiculo values (370258278,13,2,'Porsche Taycan', 4,0);"
        val insert15 = "insert into veiculo values (698333553,12,2,'Audi e-tron GT', 2,0);"
        val insert16 = "insert into veiculo values (853657730,9,2,'Tesla Model 3', 4,0);"
        val insert17 = "insert into veiculo values (439626574,8,2,'Tesla Model Y', 3,0);"
        val insert18 = "insert into veiculo values (811951021,10,2,'Volkswagen ID.3', 1,0);"
        val insert19 = "insert into veiculo values (593438176,17,4,'Trotinete V1', 4,0);"
        val insert20 = "insert into veiculo values (834339002,14,4,'Trotinete V3', 2,0);"
        val insert21 = "insert into veiculo values (761604391,13,3,'Ather 450', 2,0);"
        val insert22 = "insert into veiculo values (558100693,12,3,'Bajaj Chetak', 1,0);"
        val insert23 = "insert into veiculo values (881074808,10,3,'TVS iQube', 2,0);"
        val insert24 = "insert into veiculo values (805517378,9,3,'Revolt RV400', 4,0);"
        val insert25 = "insert into veiculo values (278678265,7,2,'Kia EV6', 2,0);"
        val insert26 = "insert into veiculo values (653315730,12,2,'Hyundai Ioniq 5', 1,0);"
        val insert27 = "insert into veiculo values (188049273,9,2,'Tesla Model 3', 3,0);"
        val insert28 = "insert into veiculo values (945692397,17,4,'Trotinete V1', 4,0);"
        db.execSQL(insert9)
        db.execSQL(insert10)
        db.execSQL(insert11)
        db.execSQL(insert12)
        db.execSQL(insert13)
        db.execSQL(insert14)
        db.execSQL(insert15)
        db.execSQL(insert16)
        db.execSQL(insert17)
        db.execSQL(insert18)
        db.execSQL(insert19)
        db.execSQL(insert20)
        db.execSQL(insert21)
        db.execSQL(insert22)
        db.execSQL(insert23)
        db.execSQL(insert24)
        db.execSQL(insert25)
        db.execSQL(insert26)
        db.execSQL(insert27)
        db.execSQL(insert28)
        val insert29 = "insert into ponto_pr values (1,'ZONA 1','Rua de Arnaldo Gama');"
        val insert30 = "insert into ponto_pr values (2,'ZONA 2','Rua da Pasteleira');"
        val insert31 = "insert into ponto_pr values (3,'ZONA 3','Rua Coronel Raúl Peres');"
        val insert32 = "insert into ponto_pr values (4,'ZONA 4','Via do Castelo do Queijo');"
        val insert33 = "insert into ponto_pr values (5,'ZONA 1','Rua de Egas Moniz');"
        val insert34 = "insert into ponto_pr values (6,'ZONA 2','Estrada Nacional 12 - N12');"
        val insert35 = "insert into ponto_pr values (7,'ZONA 3','Alameda Capitães de Abril');"
        val insert36 = "insert into ponto_pr values (8,'ZONA 4','Estrada da Circunvalação');"
        db.execSQL(insert29)
        db.execSQL(insert30)
        db.execSQL(insert31)
        db.execSQL(insert32)
        db.execSQL(insert33)
        db.execSQL(insert34)
        db.execSQL(insert35)
        db.execSQL(insert36)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS" + TABLE_NAME
        db.execSQL(DROP_TABLE)
        val DROP_TABLE1 = "DROP TABLE IF EXISTS" + TABLE_NAME2
        db.execSQL(DROP_TABLE1)
        val DROP_TABLE2 = "DROP TABLE IF EXISTS" + TABLE_NAME3
        db.execSQL(DROP_TABLE2)
        val DROP_TABLE3 = "DROP TABLE IF EXISTS" + TABLE_NAME4
        db.execSQL(DROP_TABLE3)
        val DROP_TABLE4 = "DROP TABLE IF EXISTS" + TABLE_NAME5
        db.execSQL(DROP_TABLE4)
        val DROP_TABLE5 = "DROP TABLE IF EXISTS" + TABLE_NAME6
        db.execSQL(DROP_TABLE5)
        onCreate(db)
    }

    // adiciona utilizador
    fun addTask(
        nif2: Int,
        nome: String,
        sobnome: String,
        user2: String,
        pas: String,
        ema: String
    ): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(nif, nif2)
        values.put(name, nome)
        values.put(snome, sobnome)
        values.put(username, user2)
        values.put(password, pas)
        values.put(email, ema)

        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }

    // adiciona o aluguer
    fun addaluguer(
        utilizador1: Int,
        vaiculo1: Int,
        zona_partilha1: Int
    ): Boolean {
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(utilizador, utilizador1)
        values.put(veiculo, vaiculo1)
        values.put(data_inicio, currentDate)
        values.put(data_fim, "01/01/2000")
        values.put(hora_inicio, "00:00:00")
        values.put(hora_fim, "00:00:00")
        values.put(zona_partilha, zona_partilha1)
        values.put(zona_entrega, 0)
        values.put(ativo2, 1)

        val _success = db.insert(TABLE_NAME6, null, values)
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }

    // verifica o utilizador
    fun getTask(usernam: String, pas: String): Int {
        var b = 0
        val db = writableDatabase
        val selectQuery =
            "SELECT  * FROM " + TABLE_NAME + " where " + username + " = '" + usernam + "' and " + password + " = '" + pas + "';"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val user = cursor.getString(cursor.getColumnIndexOrThrow(nif)).toString()
                if (user.isNotEmpty()) {
                    b = user.toInt()
                }
            }
        }
        cursor.close()
        return b
    }

    // vai buscar o veiculo com o  tipo e o ponto que quer para o aluguer
    fun getveiculo(tipo3: Int, poo: Int): ArrayList<String> {
        val veiculo = ArrayList<String>()

        val db = writableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_NAME2 + " Where " + tipo_veiculo + " =  " + tipo3 + " and " + ponto_atual + " = " + poo + " and " + ativo + " = 0;"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {

            var tipo2: String
            veiculo.add("Selecione uma opção...")
            if (cursor!!.moveToFirst()) {
                while (cursor.isAfterLast == false) {

                    tipo2 = cursor.getString(cursor.getColumnIndexOrThrow(modelo)).toString()


                    veiculo.add(tipo2)
                    cursor.moveToNext()
                }
            }
        }
        cursor.close()
        return veiculo
    }
    fun getveiculo2(): ArrayList<String> {
        val veiculo = ArrayList<String>()

        val db = writableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_NAME2 + " GROUP BY modelo;"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {

            var tipo2: String
            veiculo.add("Selecione uma opção...")
            if (cursor!!.moveToFirst()) {
                while (cursor.isAfterLast == false) {

                    tipo2 = cursor.getString(cursor.getColumnIndexOrThrow(modelo)).toString()


                    veiculo.add(tipo2)
                    cursor.moveToNext()
                }
            }
        }
        cursor.close()
        return veiculo
    }

    // da os tipos de veiculos da bd para o aluguer
    fun getTipoveiculo(): ArrayList<String> {
        val veiculo = ArrayList<String>()

        val db = writableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_NAME3 + ";"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            var id2: Int
            var tipo2: String
            veiculo.add("Selecione uma opção...")
            if (cursor!!.moveToFirst()) {
                while (cursor.isAfterLast == false) {
                    id2 = cursor.getString(cursor.getColumnIndexOrThrow(id_tipo)).toInt()
                    tipo2 = cursor.getString(cursor.getColumnIndexOrThrow(tipo)).toString()


                    veiculo.add(tipo2)
                    cursor.moveToNext()
                }
            }
        }
        cursor.close()
        return veiculo
    }

    // vai buscar os pontos
    fun getponto(): ArrayList<String> {
        val ponto = ArrayList<String>()

        val db = writableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_NAME5 + ";"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            var rua2: String
            ponto.add("Selecione uma opção...")
            if (cursor!!.moveToFirst()) {
                while (cursor.isAfterLast == false) {
                    rua2 = cursor.getString(cursor.getColumnIndexOrThrow(rua)).toString()
                    ponto.add(rua2)
                    cursor.moveToNext()
                }
            }
        }
        cursor.close()
        return ponto
    }

// falta os updates do aluguer
// falta os updates de colocar a dizer k o veiculo n esta disponivel
// 0 não esta a ser utilizado
// 1 esta a ser alugado passa a zero no fim do aluguer

    fun getpt(nome: String): Int {
        var b = 0
        val db = writableDatabase
        val selectQuery =
            "SELECT  * FROM " + TABLE_NAME5 + " where " + rua + " = '" + nome + "';"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val user = cursor.getString(cursor.getColumnIndexOrThrow(id_ponto)).toString()
                if (user.isNotEmpty()) {
                    b = user.toInt()
                }
            }
        }
        cursor.close()
        return b
    }

    fun gett(nome: String): Int {
        var b = 0
        val db = writableDatabase
        val selectQuery =
            "SELECT  * FROM " + TABLE_NAME3 + " where " + tipo + " = '" + nome + "';"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val user = cursor.getString(cursor.getColumnIndexOrThrow(id_tipo)).toString()
                if (user.isNotEmpty()) {
                    b = user.toInt()
                }
            }
        }
        cursor.close()
        return b
    }
    fun getv(nome: String,tipo3: Int, poo: Int): Int {
        var b = 0
        val db = writableDatabase
        val selectQuery =  "SELECT  * FROM " + TABLE_NAME2 + " where " + modelo + " = '" + nome + "' and " + tipo_veiculo + " = " + tipo3 + " and " + ponto_atual + " = " + poo + " and " + ativo + " = 0 ;"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val user = cursor.getString(cursor.getColumnIndexOrThrow(codigo)).toString()
                if (user.isNotEmpty()) {
                    b = user.toInt()
                }
            }
        }
        cursor.close()
        return b
    }
    fun getveiculo2(nif: Int):Int{
        var veiculo = 0

        val db = writableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_NAME6 + " Where " + utilizador + " =  " + nif + " and " + ativo2 + " = 1 or " + ativo2 + " = 2 or " + ativo2 + " = 3;"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {

            var tipo2: Int

            if (cursor!!.moveToFirst()) {
                while (cursor.isAfterLast == false) {
                    val user = cursor.getString(cursor.getColumnIndexOrThrow(ativo2)).toString()
                    veiculo = user.toInt()
                    cursor.moveToNext()
                }
            }else
            {
                veiculo = 0
            }
        }
        cursor.close()
        return veiculo
    }
    fun getveiculocod(nif: Int):Int{
        var veiculo1 = 0

        val db = writableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_NAME6 + " Where " + utilizador + " =  " + nif + " and " + ativo2 + " = 1;"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {

            var tipo2: Int

            if (cursor!!.moveToFirst()) {

                val tipo2 = cursor.getString(cursor.getColumnIndexOrThrow(veiculo)).toInt()
                veiculo1 = tipo2
            }
        }
        cursor.close()
        return veiculo1
    }
    fun getveiculocod2(nif: Int):Int{
        var veiculo1 = 0

        val db = writableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_NAME6 + " Where " + utilizador + " =  " + nif + " and " + ativo2 + " = 2;"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {

            var tipo2: Int

            if (cursor!!.moveToFirst()) {

                val tipo2 = cursor.getString(cursor.getColumnIndexOrThrow(veiculo)).toInt()
                veiculo1 = tipo2
            }
        }
        cursor.close()
        return veiculo1
    }
    fun getveiculocod3(mode:String):Int{
        var veiculo1 = 0

        val db = writableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_NAME2 + " Where " + modelo + " =  '" + mode + "' ;"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {

            var tipo2: Int

            if (cursor!!.moveToFirst()) {

                val tipo2 = cursor.getString(cursor.getColumnIndexOrThrow(codigo)).toInt()
                veiculo1 = tipo2
            }
        }
        cursor.close()
        return veiculo1
    }
    fun updatealuguer(id_aluguer1:Int){
        val db = this.writableDatabase
        val update = "UPDATE " + TABLE_NAME6 + " SET "+ ativo2 + " = 3 WHERE " + id_aluguer + " = " + id_aluguer1 + " and " + ativo2 + " = 1;"

        db.execSQL(update)
    }
    fun updatem(id_aluguer1:Int){
        val db = this.writableDatabase
        val update2 = "UPDATE " + TABLE_NAME6 + " SET "+ ativo2 + " = 2 WHERE " + id_aluguer + " = " + id_aluguer1 + " and " + ativo2 + " = 1;"
        db.execSQL(update2)
        val date = Calendar.getInstance().time
        var dateTimeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val update3 = "UPDATE " + TABLE_NAME6 + " SET "+ hora_inicio + " = '" + dateTimeFormat.format(date) + "' WHERE " + id_aluguer + " = " + id_aluguer1 + " and " + ativo2 + " = 2;"
        db.execSQL(update3)
        val selectQuery = "SELECT  * FROM " + TABLE_NAME6 + " Where " + id_aluguer + " =  " + id_aluguer1 + " and " + ativo2 + " = 2;"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {



            if (cursor!!.moveToFirst()) {

                val tipo2 = cursor.getString(cursor.getColumnIndexOrThrow(veiculo)).toInt()
                val update4 =
                    "UPDATE " + TABLE_NAME2 + " SET " + ativo + " = 1 WHERE " + codigo + " = " + tipo2 + " and " + ativo + " = 0;"
                db.execSQL(update4)
            }

        }
    }
    fun getveiculoalu(nif: Int):Int{
        var veiculo1 = 0

        val db = writableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_NAME6 + " Where " + utilizador + " =  " + nif + " and " + ativo2 + " = 1;"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {

            var tipo2: Int

            if (cursor!!.moveToFirst()) {

                val tipo2 = cursor.getString(cursor.getColumnIndexOrThrow(id_aluguer)).toInt()
                veiculo1 = tipo2
            }
        }
        cursor.close()
        return veiculo1
    }
    fun updatefim(id_aluguer1:Int,zona:Int,tipo2:Int){
        val db = this.writableDatabase
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())
        val date = Calendar.getInstance().time
        var dateTimeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val update2 = "UPDATE " + TABLE_NAME6 + " SET " + ativo2 + " = 3, " + zona_entrega + " = " + zona + ", " + data_fim + " = '" + currentDate + "', " + hora_fim + " = '" + dateTimeFormat.format(date) + "'  WHERE " + id_aluguer + " = " + id_aluguer1 + " and " + ativo2 + " = 2;"
        db.execSQL(update2)
        val update3 = "UPDATE " + TABLE_NAME2 + " SET " + ativo + " = 0, " + ponto_atual + " = " + zona + " Where  " + codigo + " = " + tipo2 + " and ativo = 1;"
        db.execSQL(update3)

    }
    fun getveiculoalu2(nif: Int):Int{
        var veiculo1 = 0

        val db = writableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_NAME6 + " Where " + utilizador + " =  " + nif + " and " + ativo2 + " = 2;"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {

            var tipo2: Int

            if (cursor!!.moveToFirst()) {

                val tipo2 = cursor.getString(cursor.getColumnIndexOrThrow(id_aluguer)).toInt()
                veiculo1 = tipo2
            }
        }
        cursor.close()
        return veiculo1
    }
    fun getprecoh(codigo: Int):Double{
        var veiculo1 = 0.0

        val db = writableDatabase
        val selectQuery = "SELECT preco from precario where tipo_precario = (select id_tipo from tipo_veiculo where id_tipo = (select tipo_veiculo from veiculo where codigo = " + codigo + "))"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {

            var tipo2: Double

            if (cursor!!.moveToFirst()) {

                val tipo2 = cursor.getString(cursor.getColumnIndexOrThrow(preco)).toDouble()
                veiculo1 = tipo2
            }
        }
        cursor.close()
        return veiculo1
    }
    fun gethoras(idalu: Int):Double{
        var veiculo1 = 0.0

        val db = writableDatabase
        val selectQuery = "SELECT  data_fim - data_fim as date,hora_fim - hora_fim as hour, hora_fim,hora_inicio FROM " + TABLE_NAME6 + " Where " + id_aluguer + " =  " + idalu + " ;"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {

            if (cursor!!.moveToFirst()) {

                var dias = cursor.getString(cursor.getColumnIndexOrThrow("date")).toInt()
                var horas = cursor.getString(cursor.getColumnIndexOrThrow("hour")).toInt()
                val horaf = cursor.getString(cursor.getColumnIndexOrThrow("hora_fim")).toString()
                val horai = cursor.getString(cursor.getColumnIndexOrThrow("hora_inicio")).toString()
                horas = horas + (24 * dias)
                var f = ArrayList<String>()
                f = horai.split(":") as ArrayList<String>
                var horas1 = f[0]
                var minutos1 = f[1]
                var v = ArrayList<String>()
                v = horaf.split(":") as ArrayList<String>
                var horas2 = v[0]
                var minutos2 = v[1]
                if(horas1 == horas2) {
                    veiculo1 = (minutos2.toDouble() - minutos1.toDouble())*0.01
                }
                else
                {
                    var h = horas2.toInt()-horas1.toInt() + (24*dias)
                    veiculo1 = h + (((60 - minutos1.toDouble()) + minutos2.toDouble())*0.01)
                }

            }
        }
        cursor.close()
        return veiculo1
    }
    fun getconsumo(codigo: Int):Double{
        var veiculo1 = 0.0

        val db = writableDatabase
        val selectQuery = "SELECT * from veiculo where  codigo = " + codigo + ";"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {

            var tipo2: Double

            if (cursor!!.moveToFirst()) {

                val tipo2 = cursor.getString(cursor.getColumnIndexOrThrow(consumo)).toDouble()
                veiculo1 = tipo2
            }
        }
        cursor.close()
        return veiculo1
    }
    fun gettipo(codigo: Int):String{
        var veiculo1 = ""

        val db = writableDatabase
        val selectQuery = "select * from tipo_veiculo where id_tipo = (select tipo_veiculo from veiculo where codigo = " + codigo + ")"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {

            var tipo2: Double

            if (cursor!!.moveToFirst()) {

                val tipo2 = cursor.getString(cursor.getColumnIndexOrThrow(tipo)).toString()
                veiculo1 = tipo2
            }
        }
        cursor.close()
        return veiculo1
    }
    fun guardarnif(nif8: Int){
        nif5 = nif8
    }
    fun recebernif():Int{
        return nif5
    }
    fun updatepass(pass3:String,nif6:Int){
        val db = this.writableDatabase
        val update = "UPDATE " + TABLE_NAME + " SET "+ password + " = '" + pass3 + "' WHERE " + nif + " = " + nif6 + ";"
        db.execSQL(update)
    }
}