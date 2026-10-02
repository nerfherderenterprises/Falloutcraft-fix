# Falloutcraft Reduxe

*Minecraft 1.19.2 · Forge 43+ · fixed & maintainable rebuild of Falloutcraft 1.1.8*

The original Falloutcraft works and builds a proper Fallout world — this is
the cleaned-up continuation of it: decompiled bytecode converted back into a
real, compiling, Mojmap-named project (the intentional "Reduxe" is a wink at
"Redux": the original wasn't garbage, it just needed a proper home).

---

## Why is there an AGENTS.md in this jar?

You may have spotted `AGENTS.md` at the root of this jar and wondered what it
is doing there. It isn't a leftover — it's a deliberate part of the workspace,
and it's a technique worth stealing if you work with an AI coding agent.

### What it's actually for

Not a Jarvis project, and not an innovation — this is standing on the
shoulders of giants: plain markdown, absolute paths, and whatever agent
tool you already run. The point is mundane continuity — **the same agent,
everywhere.** The session that fixes this mod is also the session that
checks costume-build material prices or updates your calendar, because
its identity is a pointer to files you own, not a seat inside one tool.

### The technique

`AGENTS.md` is a **workspace identity shim for AI coding agents**. Most agent
setups throw away everything between sessions: every new conversation starts
blank, and you re-explain the project, your preferences, and the state of the
work every single time. The shim fixes that with two small, plain-text pieces:

1. **`AGENTS.md`** (this file, at the workspace root) — the first thing the
   agent reads. It says *who it is here*, *what this project is*, and *where
   the standing context lives*. It stays short on purpose.
2. **A journal + handbook at an absolute path** outside the project — a
   `MEMORY.md` index of dated entries (what happened, what was decided,
   where minds changed) and an `AGENTS.md`-style handbook (persona, rules,
   permissions). The workspace shim points at both and nothing more.

### Why it works

- **Identity travels; context does not.** The agent's *self* is referenced by
  a stable pointer, not copied into every session. Heavy context stays on
  disk and loads by need — the session starts lean but never starts lost.
- **The session-start read is the whole ritual.** The shim instructs the
  agent to read the journal index first thing. One file read later, it knows
  who it is, what's in flight, and what was ruled yesterday.
- **Writes append to one journal by absolute path** — never a fork inside the
  project. Your memory ends up in *one* place you own, as plain files, with
  no lock-in to any tool.
- **Plain text everywhere.** Markdown files, absolute paths, no database.
  Move the folder, swap the agent tool, edit it by hand — it all still works.

### Adapting it to your own agent

1. Copy this `AGENTS.md` into the root of any project you use an agent in.
2. Replace the journal/handbook paths with **your own** absolute paths
   (the paths in this copy are the original author's — point them at yours).
3. Create a `MEMORY.md` with one line: *"Read this file at session start.
   Entries are dated files in `entries/`."* — then just start writing dated
   entries as things happen.
4. Put your standing rules (permissions, tone, doctrine) in a handbook file
   the shim also points to.

That's it. The agent now remembers between sessions, the memory is yours as
long as plain files are readable, and every project gets the same cheap
onboarding: one pointer file at the root.

---

*Original mod by TATHAN (github.com/TathanDev/FalloutCraft). Reduxe =
fixed sources, declared dependencies (incl. TerraBlender), cleaned-up
decompile artifacts, and this jar with its workflow notes intact.*
