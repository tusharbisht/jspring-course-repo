# Hidden Grading Harness

This directory contains the **automated grader** for this course.

## Do not peek if you're a learner

The `exercise-NN-*/` subdirectories hold hidden test classes that grade
your work. Reading them is technically possible but defeats the point of
the exercise — your future colleagues won't have a hidden test to peek
at when you're shipping production code.

## How it runs

```bash
bash .grading/run-grading.sh exercise-01-fix-n-plus-one
```

Stages the hidden test class, runs `./mvnw test`, parses the JUnit XML
output, emits `RESULT: PASS` or `RESULT: FAIL` (last line of stdout) plus
`grading-result.json`, and unstages the test class. Exit code matches
the RESULT.

The skeleton implements the universal grading contract documented at
`tools/grading-skeletons/README.md` in the Skillslab platform repo.

## Course-author notes

- Each exercise has its own `exercise-NN-<slug>/` subdirectory.
- Hidden tests use real Spring Testing primitives (`@SpringBootTest`,
  Hibernate `Statistics`, MockMvc) — never regex on source code.
- For non-test-framework exercises (slash commands, MCP wiring, hooks),
  add a `verify.sh` to the exercise dir; the runner dispatches to it.
- Per-branch SUT state is intentional: starter branches FAIL the
  hidden tests; solution branches PASS them.

## Why dot-prefixed

Prevents accidental browsing in IDEs that hide dotfiles. Combined with
`.gitattributes linguist-vendored`, GitHub's UI also collapses this
directory by default.
