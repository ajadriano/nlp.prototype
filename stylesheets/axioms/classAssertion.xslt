<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="S[count(S)=0][NP[count(DT) = 0 and count(CC) = 0 and (NNP|NNPS|PRP)[@nsubj]] and VP[(VB|VBZ|VBP)[@cop and @lemma='be'] and NP[count(PP)=0]]]">
ClassAssertion(
    <xsl:apply-templates select="VP"/>
    <xsl:text> </xsl:text>
    <xsl:call-template name="noun_phrase_to_individual">
            <xsl:with-param name="NP" select="NP" />
    </xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(S)=0][VP/..//@id=@root and NP[count(DT) = 0 and count(CC) = 1 and (NNP|NNPS|PRP)[@nsubj]] and VP[(VB|VBZ|VBP)[@cop and @lemma='be']]]">
ClassAssertion(
    <xsl:apply-templates select="VP"/>
    <xsl:text> </xsl:text>
    <xsl:call-template name="noun_phrase_to_individual_before_cc">
            <xsl:with-param name="NP" select="NP" />
    </xsl:call-template>)
ClassAssertion(
    <xsl:apply-templates select="VP"/>
    <xsl:text> </xsl:text>
    <xsl:call-template name="noun_phrase_to_individual_after_cc">
            <xsl:with-param name="NP" select="NP" />
    </xsl:call-template>)
</xsl:template>

</xsl:stylesheet>