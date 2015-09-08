package jpa.study.dao.ch07

import java.util.Date
import javax.persistence.MappedSuperclass

MappedSuperclass
public interface DateTimeParanoidModel : DateTimeModel {
    public var
            deletedAt: Date?
}