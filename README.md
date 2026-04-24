# Module 4 — Hooks for Spring Boot Gotchas

Wire the three hooks in `.claude/settings.json`:
- **PreToolUse**: block any Edit to `application-prod.properties` (exit 2 + message)
- **PostToolUse**: run `./mvnw spotless:apply` after any .java edit (exit 0)
- **Stop**: run `./mvnw test` on the affected module when Claude is about to exit

Use bash by default. Python alternative shown for cleaner JSON parsing.
