# Module 3 — Slash Commands + Custom Subagents for Java

Two exercises:
1. Author `.claude/commands/controller-review.md` — a reusable prompt that audits any controller class for missing @Valid / unhandled exceptions / manual auth / N+1 risks.
2. Author `.claude/agents/mockito-test-writer.md` — a scoped subagent with YAML frontmatter + a tool allowlist that writes Mockito 5 / JUnit 5 tests for any service class.

Run `/controller-review UserController` and paste the output. Run your subagent against `OrderService` and paste the generated test.
