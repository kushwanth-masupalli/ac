# =========================
# 1. READ CSV & LIMIT URLS
# =========================

import pandas as pd

df = pd.read_csv("technology_data.csv")

# 4th column contains URLs (0-based index)
urls = df.iloc[:, 3].dropna().tolist()
urls = urls[:100]   # limit URLs
print("Total URLs:", len(urls))


# =========================
# 2. SCRAPE ARTICLES
# =========================

import trafilatura
def scrape_article(url):
    try:
        downloaded = trafilatura.fetch_url(url)
        if downloaded:
            return trafilatura.extract(downloaded)
        return None
    except:
        return None
articles = []
for url in urls:
    text = scrape_article(url)
    if text and text.strip():
        articles.append(text)

print("Scraped articles:", len(articles))


# =========================
# 3. TEXT PREPROCESSING
# =========================

import re
import nltk
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize

nltk.download("punkt")
nltk.download("punkt_tab")
nltk.download("stopwords")

stop_words = set(stopwords.words("english"))

def preprocess(text):
    text = text.lower()
    text = re.sub(r"<.*?>", "", text)          # remove HTML tags
    text = re.sub(r"[^a-z\s]", "", text)       # remove punctuation & numbers
    tokens = word_tokenize(text)
    tokens = [w for w in tokens if w not in stop_words and len(w) > 2]
    return tokens


tokenized_articles = [preprocess(article) for article in articles]


# =========================
# 4. CBOW SAMPLE CREATION
# =========================
# CBOW: many → one

def generate_cbow_samples(tokens, k=2):
    samples = []
    for i in range(k, len(tokens) - k):
        context = tokens[i-k:i] + tokens[i+1:i+k+1]
        target = tokens[i]
        samples.append((context, target))
    return samples


cbow_samples = []
for tokens in tokenized_articles:
    cbow_samples.extend(generate_cbow_samples(tokens, k=2))

print("CBOW samples:", len(cbow_samples))


# =========================
# 5. SKIP-GRAM SAMPLE CREATION
# =========================
# Skip-Gram: one → many

def generate_skipgram_samples(tokens, k=2):
    samples = []
    for i in range(k, len(tokens) - k):
        center = tokens[i]
        context = tokens[i-k:i] + tokens[i+1:i+k+1]
        for word in context:
            samples.append((center, word))
    return samples


skipgram_samples = []
for tokens in tokenized_articles:
    skipgram_samples.extend(generate_skipgram_samples(tokens, k=2))

print("Skip-Gram positive samples:", len(skipgram_samples))


# =========================
# 6. NEGATIVE SAMPLING
# =========================

import random
random.seed(42)
# Vocabulary
vocab = list(set(word for tokens in tokenized_articles for word in tokens))
def generate_negative_samples(center, positive_word, vocab, num_neg=2):
    negatives = []
    while len(negatives) < num_neg:
        neg = random.choice(vocab)
        if neg != positive_word:
            negatives.append((center, neg))
    return negatives
skipgram_training_data = []
for center, context in skipgram_samples:
    # positive sample
    skipgram_training_data.append((center, context, 1))

    # negative samples
    negatives = generate_negative_samples(center, context, vocab)
    for neg in negatives:
        skipgram_training_data.append((neg[0], neg[1], 0))

print("Skip-Gram + negative samples:", len(skipgram_training_data))


# =========================
# 7. SAVE OUTPUT CSV FILES
# =========================

# ---- CBOW CSV ----
cbow_rows = []
for context, target in cbow_samples:
    cbow_rows.append(context + [target])

cbow_df = pd.DataFrame(
    cbow_rows,
    columns=["context_1", "context_2", "context_3", "context_4", "target"]
)

cbow_df.to_csv("cbow_training_data.csv", index=False)


# ---- Skip-Gram Positive CSV ----
skipgram_pos_df = pd.DataFrame(
    skipgram_samples,
    columns=["input", "output"]
)

skipgram_pos_df.to_csv("skipgram_positive_samples.csv", index=False)


# ---- Skip-Gram + Negative CSV ----
skipgram_neg_df = pd.DataFrame(
    skipgram_training_data,
    columns=["input", "output", "label"]
)

skipgram_neg_df.to_csv("skipgram_negative_samples.csv", index=False)


print("\nCSV files generated successfully:")
print("1. cbow_training_data.csv")
print("2. skipgram_positive_samples.csv")
print("3. skipgram_negative_samples.csv")

