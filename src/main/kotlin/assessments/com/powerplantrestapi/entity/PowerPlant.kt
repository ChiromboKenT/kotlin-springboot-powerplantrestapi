package assessments.com.powerplantrestapi.entity

import org.hibernate.Hibernate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "powerplant")
data class PowerPlant(

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val sequenceNumber: Int,

    @Column(name = "generatorId")
    var generatorId: String,

    @Column(name = "plantName")
    var plantName: String,

    @Column(name = "pstatabb")
    var pstatabb: String,

    @Column(name = "year")
    var year: String,

    @Column(name = "generatorStatus")
    var generatorStatus: String,

    @Column(name = "genntan")
    var genntan: String,

    @Column(name = "created_at")
    var createdAt: Date,

    @Column(name = "updated_at")
    var updatedAt: Date?

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as PowerPlant

        return sequenceNumber == other.sequenceNumber
    }

    override fun hashCode(): Int = 0
}
