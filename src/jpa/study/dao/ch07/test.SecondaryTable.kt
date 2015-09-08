package jpa.study.dao.ch07

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.PrimaryKeyJoinColumn
import javax.persistence.SecondaryTable

Entity
SecondaryTable(name = "board_detail"/*, pkJoinColumns = arrayOf(PrimaryKeyJoinColumn(name = "board_detail_id"))*/)
public class Board : TypeModel() {
    public Column(table = "board_detail") var
            content: String? = null
}