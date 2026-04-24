# Claude Code for Spring Boot — Course Repo

Pick the module branch for the step you're on:

- `module-0-preflight` — Run `java -version` + `mvn -v`, paste outputs
- `module-1-starter` — OrderService with planted N+1 bug (no CLAUDE.md yet)
- `module-2-claudemd` — Same bug; you author `CLAUDE.md` for this repo
- `module-3-agents` — `.claude/commands/` + `.claude/agents/` stubs
- `module-4-hooks` — `.claude/settings.json` with three hook stubs
- `module-5-mcp` — ready for `claude mcp add --transport stdio team-tickets`
- `module-6-capstone` — POST /orders scaffolding + GHA `lab-grade.yml`

Each branch includes this README and the starter pom.xml. See the course for instructions.

## Prerequisites
- Java 21 (Temurin recommended)
- Maven 3.9+
- Claude CLI (`npm i -g @anthropic-ai/claude-code` or equivalent)
- An Anthropic API key via `claude /login` or `ANTHROPIC_API_KEY` env var
