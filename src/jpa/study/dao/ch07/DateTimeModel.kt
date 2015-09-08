package jpa.study.dao.ch07

import java.util.Date
import javax.persistence.Column
import javax.persistence.MappedSuperclass

MappedSuperclass
public interface DateTimeModel : BaseModel {
    public var
            createdAt: Date
    public var
            updatedAt: Date?
}