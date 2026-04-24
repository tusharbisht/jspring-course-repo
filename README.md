# Module 1 — Feel the Pain: Claude Code on Your Repo with No Context

You're working in `OrderService.getRecentOrders()`. Your team uses this method on the customer dashboard. It just got flagged for slow response times in staging — every call triggers ~21 database queries for 20 orders.

**Your task** (~20 min):
1. Ask Claude Code to find + fix the bug. Use `/help`, `/init`, whatever feels natural.
2. **DO NOT** author `CLAUDE.md` yet — you'll do that in Module 2.
3. Paste (a) the prompt you used, (b) the diff Claude produced, (c) any edits you had to make by hand because Claude didn't know your team's conventions.

What you're observing: without a CLAUDE.md, Claude will guess at your test framework, your namespace conventions (javax.* vs jakarta.*), your transaction boundaries, etc. Whatever it guesses WRONG is what you'll add to CLAUDE.md in the next module.

## Starting the sim
```
./mvnw clean verify     # confirm tests pass before you start
claude                  # fire up Claude Code
```
