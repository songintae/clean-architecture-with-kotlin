package buckpal.common

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Component


@kotlin.annotation.Target(AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class UseCase(
    @get: AliasFor(annotation = Component::class) val value: String = ""
)
