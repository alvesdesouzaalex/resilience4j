package com.store.cbapp.config.resilience4j

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.core.registry.EntryAddedEvent
import io.github.resilience4j.core.registry.EntryRemovedEvent
import io.github.resilience4j.core.registry.EntryReplacedEvent
import io.github.resilience4j.core.registry.RegistryEventConsumer
import mu.KLogging
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component


@Component
class CircuitBreakerRegistryStatusChange {

    companion object : KLogging()

    @Bean
    fun circuitBreakerRegistryChangeEventConsumer(): RegistryEventConsumer<CircuitBreaker> {
        return object : RegistryEventConsumer<CircuitBreaker> {
            override fun onEntryAddedEvent(entryAddedEvent: EntryAddedEvent<CircuitBreaker>) {
                entryAddedEvent.addedEntry
                    .eventPublisher
                    .onStateTransition {
                        logger.info {
                            ">>> CircuitBreakerOnStateTransitionEvent[${it.circuitBreakerName}] from: ${it.stateTransition.fromState} to: ${it.stateTransition.toState} <<<"
                        }
                    }
            }

            override fun onEntryRemovedEvent(entryRemoveEvent: EntryRemovedEvent<CircuitBreaker>) {}
            override fun onEntryReplacedEvent(entryReplacedEvent: EntryReplacedEvent<CircuitBreaker>) {}

        }
    }

}
