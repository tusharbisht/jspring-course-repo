# Module 5 — MCP for Team Context

Wire the pre-built **team-tickets** MCP into Claude Code:
```
git clone https://github.com/tusharbisht/aie-team-tickets-mcp
cd aie-team-tickets-mcp && npm install
cd -   # back to the course repo
claude mcp add --transport stdio team-tickets -- node <path-to>/aie-team-tickets-mcp/server.js
claude /mcp list    # verify it appears
```

Then from inside `claude`:
> "Find recent tickets tagged `payments-api` and tell me which one I should pick up next. Explain the priority signal."

Paste the transcript showing Claude's tool_use against `team-tickets` + your follow-up decision.
