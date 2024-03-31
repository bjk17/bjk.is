# bjk.is
> *My personal and professional website*

### Updating the site
Most of the files are static HTML and won't be changed often.
I do however update my CV from time to time and at the same time
I like to update the date in the footer of the front page
to indicate the changes to the site.
So after editing `cv.tex` I run:

```bash
make
git add cv.tex cv.pdf cv-full.pdf index.html 
git commit --message "Updated CV"
git push
```